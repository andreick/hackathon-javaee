package com.stefanini.exception.usuario;

import javax.ws.rs.NotFoundException;

public class UsuarioNotFoundException extends NotFoundException {

    public UsuarioNotFoundException(Long id) {
        super("Usuário com id " + id + " não encontrado");
    }
}
