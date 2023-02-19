package com.stefanini.service;

import com.stefanini.dao.UsuarioDAO;
import com.stefanini.dto.usuario.UsuarioCreateDTO;
import com.stefanini.dto.usuario.UsuarioDetailsDTO;
import com.stefanini.dto.usuario.UsuarioUpdateDTO;
import com.stefanini.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
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
        return usuarios.stream().map(UsuarioDetailsDTO::new).collect(Collectors.toList());
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

    private Usuario findByIdOrThrow(Long id) {
        var usuario = usuarioDAO.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário com id " + id + " não encontrado");
        }
        return usuario;
    }
}
