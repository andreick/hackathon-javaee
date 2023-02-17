package com.stefanini.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class UsuarioCreateDTO {

    private final String nome;
    private final String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate dataNascimento;

    private final String login;
    private final String senha;

    public UsuarioCreateDTO(String nome, String email, LocalDate dataNascimento, String login, String senha) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
