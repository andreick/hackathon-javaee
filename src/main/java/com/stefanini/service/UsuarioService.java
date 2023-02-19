package com.stefanini.service;

import com.stefanini.dao.UsuarioDAO;
import com.stefanini.dto.usuario.UsuarioCreateDTO;
import com.stefanini.dto.usuario.UsuarioDetailsDTO;
import com.stefanini.dto.usuario.UsuarioUpdateDTO;
import com.stefanini.exception.usuario.UsuarioNotFoundException;
import com.stefanini.exception.util.InvalidMonthException;
import com.stefanini.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService {

    @Inject
    private UsuarioDAO usuarioDAO;

    public UsuarioDetailsDTO save(UsuarioCreateDTO dto) {
        var usuario = dto.toUsuario();
        usuarioDAO.save(usuario);
        return new UsuarioDetailsDTO(usuario);
    }

    public List<UsuarioDetailsDTO> listAll() {
        List<Usuario> usuarios = usuarioDAO.listAll();
        return mapAllToUsuarioDetailsDTO(usuarios);
    }

    public UsuarioDetailsDTO findById(Long id) {
        var usuario = findByIdOrThrow(id);
        return new UsuarioDetailsDTO(usuario);
    }

    public UsuarioDetailsDTO update(Long id, UsuarioUpdateDTO dto) {
        var usuario = findByIdOrThrow(id);
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuarioDAO.update(usuario);
        return new UsuarioDetailsDTO(usuario);
    }

    public void delete(Long id) {
        var usuario = findByIdOrThrow(id);
        usuarioDAO.delete(usuario);
    }

    public List<UsuarioDetailsDTO> listBirthdayPersons(Integer month) {
        int monthValue = (month != null) ? month : LocalDate.now().getMonthValue();
        if (monthValue < 1 || monthValue > 12) {
            throw new InvalidMonthException(monthValue);
        }
        var usuarios = usuarioDAO.listBirthDayPersonsByMonth(monthValue);
        return mapAllToUsuarioDetailsDTO(usuarios);
    }

    public List<String> listEmailProviders() {
        return usuarioDAO.listEmailProviders();
    }

    private Usuario findByIdOrThrow(Long id) {
        var usuario = usuarioDAO.findById(id);
        if (usuario == null) {
            throw new UsuarioNotFoundException(id);
        }
        return usuario;
    }

    private List<UsuarioDetailsDTO> mapAllToUsuarioDetailsDTO(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDetailsDTO::new).collect(Collectors.toList());
    }
}
