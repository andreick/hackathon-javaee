package com.stefanini.dto.usuario;

import com.stefanini.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UsuarioCreateDTO {

    @NotBlank
    @Size(max = 50)
    private final String nome;

    @NotBlank
    @Email
    @Size(min = 10, max = 255)
    private final String email;

    @Past
    private final LocalDate dataNascimento;

    @NotBlank
    @Size(min = 5, max = 20)
    private final String login;

    @NotBlank
    @Size(min = 4, max = 10)
    private final String senha;

    public UsuarioCreateDTO(String nome, String email, LocalDate dataNascimento, String login, String senha) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.login = login;
        this.senha = senha;
    }

    public Usuario toUsuario() {
        return new Usuario(null, nome, login, email, senha, dataNascimento);
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
