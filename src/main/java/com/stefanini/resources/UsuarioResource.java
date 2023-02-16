package com.stefanini.resources;

import com.stefanini.dao.usuario.UsuarioDAO;
import com.stefanini.dto.usuario.UsuarioCreateDTO;
import com.stefanini.dto.usuario.UsuarioDetailsDTO;
import com.stefanini.model.Usuario;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/usuarios")
public class UsuarioResource {

    private final UsuarioDAO usuarioDAO;

    @Inject
    public UsuarioResource(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UsuarioCreateDTO dto) {
        var usuario = new Usuario(dto);
        usuarioDAO.save(usuario);
        return Response.ok(new UsuarioDetailsDTO(usuario)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        List<Usuario> usuarios = usuarioDAO.listAll();
        var dtos = usuarios.stream().map(UsuarioDetailsDTO::new).collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") Long id) {
        Usuario usuario = usuarioDAO.findById(id);
        if (usuario == null) {
            String message = "Usuário com id " + id + " não encontrado";
            return Response.status(404).entity(message).build();
        }
        var dto = new UsuarioDetailsDTO(usuario);
        return Response.ok(dto).build();
    }
}
