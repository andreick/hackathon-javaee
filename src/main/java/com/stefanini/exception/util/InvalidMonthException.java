package com.stefanini.exception.util;

import javax.ws.rs.BadRequestException;

public class InvalidMonthException extends BadRequestException {

    public InvalidMonthException(int month) {
        super("Mês " + month + " inválido");
    }
}
