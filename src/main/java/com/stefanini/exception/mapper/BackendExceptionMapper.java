package com.stefanini.exception.mapper;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BackendExceptionMapper implements ExceptionMapper<Exception> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        var error = new ErrorResponse("Erro não tratado", uriInfo.getPath());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
}
