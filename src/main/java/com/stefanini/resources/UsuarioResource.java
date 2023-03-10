package com.stefanini.resources;

import com.stefanini.dto.usuario.UsuarioCreateDTO;
import com.stefanini.dto.usuario.UsuarioDetailsDTO;
import com.stefanini.dto.usuario.UsuarioUpdateDTO;
import com.stefanini.service.usuario.UsuarioService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private UsuarioService usuarioService;

    @POST
    public Response create(@Valid UsuarioCreateDTO dto, @Context UriInfo uriInfo) {
        UsuarioDetailsDTO usuario = usuarioService.create(dto);
        var uriBuilder = uriInfo.getBaseUriBuilder();
        uriBuilder.path(usuario.getId().toString()); // Cabeçalho Location
        return Response.created(uriBuilder.build()).entity(usuario).build();
    }

    @GET
    public Response readAll(@Context UriInfo uriInfo) {
        List<UsuarioDetailsDTO> usuarios = usuarioService.getAll(uriInfo);
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/{id}")
    public Response read(@PathParam("id") Long id) {
        UsuarioDetailsDTO usuario = usuarioService.getById(id);
        return Response.ok(usuario).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid UsuarioUpdateDTO dto) {
        UsuarioDetailsDTO usuario = usuarioService.update(id, dto);
        return Response.ok(usuario).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        usuarioService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/aniversariantes")
    public Response readAllBirthdayPersons(@QueryParam("mes") Integer month) {
        List<UsuarioDetailsDTO> usuarios = usuarioService.getBirthdayPersons(month);
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/provedores-email")
    public Response readAllEmailProviders() {
        List<String> providers = usuarioService.getEmailProviders();
        return Response.ok(providers).build();
    }
}
