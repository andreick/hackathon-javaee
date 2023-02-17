package com.stefanini.resources;

import com.stefanini.dto.usuario.UsuarioCreateDTO;
import com.stefanini.dto.usuario.UsuarioDetailsDTO;
import com.stefanini.service.UsuarioService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/usuarios")
public class UsuarioResource {

    @Inject
    private UsuarioService usuarioService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UsuarioCreateDTO dto, @Context UriInfo uriInfo) {
        UsuarioDetailsDTO usuario = usuarioService.save(dto);
        var uriBuilder = uriInfo.getBaseUriBuilder();
        uriBuilder.path(usuario.getId().toString()); // Cabeçalho Location
        return Response.created(uriBuilder.build()).entity(usuario).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        List<UsuarioDetailsDTO> usuarios = usuarioService.listAll();
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readOne(@PathParam("id") Long id) {
        UsuarioDetailsDTO usuario = usuarioService.findById(id);
        return Response.ok(usuario).build();
    }
}
