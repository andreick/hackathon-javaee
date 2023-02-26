package com.stefanini.service.usuario;

import com.stefanini.model.Usuario;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

public interface UsuarioQuery {

    String findParam(MultivaluedMap<String, String> queryParameters);

    List<Usuario> query(String param);
}
