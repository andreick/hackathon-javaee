package com.stefanini.service.usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsuarioQueryService {

    List<UsuarioQuery> getQueryParameters() {
        return List.of(
                new LoginQuery()
        );
    }
}
