package com.stefanini.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stefanini.model.Usuario;

import java.time.LocalDate;

public class UsuarioDetailsDTO {

    private final Long id;
    private final String nome;
    private final String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate dataNascimento;

    public UsuarioDetailsDTO(Long id, String nome, String email, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public UsuarioDetailsDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getDataNascimento());
    }

    public Long getId() {
        return id;
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
}
