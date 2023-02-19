package com.stefanini.exception.mapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(WebApplicationException exception) {
        int status = exception.getResponse().getStatus();
        var error = new ErrorResponse(exception.getMessage(), uriInfo.getPath());
        return Response.status(status).entity(error).build();
    }
}
