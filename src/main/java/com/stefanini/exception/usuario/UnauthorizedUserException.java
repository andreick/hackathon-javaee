package com.stefanini.exception.usuario;

import io.quarkus.security.UnauthorizedException;

public class UnauthorizedUserException extends UnauthorizedException {

    public UnauthorizedUserException() {
        super("Usuário não autenticado");
    }
}
