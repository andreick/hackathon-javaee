package com.stefanini.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CredenciaisDTO {

    @NotBlank
    @Size(min = 5, max = 20)
    private final String login;

    @NotBlank
    @Size(min = 4, max = 10)
    private final String senha;

    public CredenciaisDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
