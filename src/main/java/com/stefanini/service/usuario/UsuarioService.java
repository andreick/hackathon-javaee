package com.stefanini.service.usuario;

import com.stefanini.dao.UsuarioDAO;
import com.stefanini.dto.CredenciaisDTO;
import com.stefanini.dto.usuario.UsuarioCreateDTO;
import com.stefanini.dto.usuario.UsuarioDetailsDTO;
import com.stefanini.dto.usuario.UsuarioUpdateDTO;
import com.stefanini.exception.usuario.UnauthorizedUserException;
import com.stefanini.exception.usuario.UsuarioNotFoundException;
import com.stefanini.exception.util.InvalidMonthException;
import com.stefanini.model.Usuario;
import com.stefanini.service.EncryptionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private EncryptionService encryptionService;

    public UsuarioDetailsDTO create(UsuarioCreateDTO dto) {
        var usuario = dto.toUsuario();
        usuario.setSenha(encryptionService.encode(usuario.getSenha()));
        usuarioDAO.save(usuario);
        return new UsuarioDetailsDTO(usuario);
    }

    public List<UsuarioDetailsDTO> getAll(UriInfo uriInfo) {
        var usuarios = query(uriInfo);
        return mapAllToUsuarioDetailsDTO(usuarios);
    }

    private List<Usuario> query(UriInfo uriInfo) {
        var queryParameters = uriInfo.getQueryParameters();
        String login = queryParameters.getFirst("login");
        if (login != null) {
            var usuario = usuarioDAO.findByLogin(login).orElseThrow(UsuarioNotFoundException::new);
            return List.of(usuario);
        }
        String nameStart = queryParameters.getFirst("nome_comeca_com");
        if (nameStart != null) {
            return usuarioDAO.listByNameLike(nameStart + "%");
        }
        return usuarioDAO.listAll();
    }

    public UsuarioDetailsDTO getById(Long id) {
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

    public void authenticate(CredenciaisDTO dto) {
        var usuario = usuarioDAO.findByLogin(dto.getLogin()).orElseThrow(UnauthorizedUserException::new);
        String senha = encryptionService.decode(usuario.getSenha());
        if (!senha.equals(dto.getSenha())) {
            throw new UnauthorizedUserException();
        }
    }

    public List<UsuarioDetailsDTO> getBirthdayPersons(Integer month) {
        int monthValue = (month != null) ? month : LocalDate.now().getMonthValue();
        if (monthValue < 1 || monthValue > 12) {
            throw new InvalidMonthException(monthValue);
        }
        var usuarios = usuarioDAO.listBirthDayPersonsByMonth(monthValue);
        return mapAllToUsuarioDetailsDTO(usuarios);
    }

    public List<String> getEmailProviders() {
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
