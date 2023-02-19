package com.stefanini.exception.mapper;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

// Evita que a exceção seja tratada pelo NotFoundExceptionMapper do Quarkus
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(NotFoundException exception) {
        int status = exception.getResponse().getStatus();
        var error = new ErrorResponse(exception.getMessage(), uriInfo.getPath());
        return Response.status(status).entity(error).build();
    }
}
