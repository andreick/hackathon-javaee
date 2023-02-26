package com.stefanini.service.usuario;

import com.stefanini.model.Usuario;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

public class LoginQuery implements UsuarioQuery {

    @Override
    public String findParam(MultivaluedMap<String, String> queryParameters) {
        return queryParameters.getFirst("login");
    }

    @Override
    public List<Usuario> query(String param) {
        return null;
    }
}
