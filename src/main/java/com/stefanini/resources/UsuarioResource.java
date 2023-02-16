package com.stefanini.resources;

import com.stefanini.dao.usuario.UsuarioDAO;
import com.stefanini.dto.usuario.UsuarioDetailsDTO;
import com.stefanini.model.Usuario;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/usuarios")
public class UsuarioResource {

    private final UsuarioDAO usuarioDAO;

    @Inject
    public UsuarioResource(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioDetailsDTO> listAll() {
        List<Usuario> usuarios = usuarioDAO.listAll();
        return usuarios.stream().map(UsuarioDetailsDTO::new).collect(Collectors.toList());
    }
}
