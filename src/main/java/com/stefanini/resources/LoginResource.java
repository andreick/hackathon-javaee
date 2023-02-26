package com.stefanini.resources;

import com.stefanini.dto.CredenciaisDTO;
import com.stefanini.service.usuario.UsuarioService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    private UsuarioService usuarioService;

    @POST
    public Response login(CredenciaisDTO dto) {
        usuarioService.authenticate(dto);
        return Response.ok().header(HttpHeaders.AUTHORIZATION, dto.getLogin()).build();
    }
}
