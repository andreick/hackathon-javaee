package com.stefanini.dto.usuario;

import com.stefanini.validation.constraints.DateFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UsuarioUpdateDTO {

    @NotBlank
    @Size(max = 50)
    private final String nome;

    @NotBlank
    @Email
    @Size(min = 10, max = 255)
    private final String email;

    @DateFormat(pattern = "yyyy-MM-dd")
    private final String dataNascimento;

    public UsuarioUpdateDTO(String nome, String email, String dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento != null ? LocalDate.parse(dataNascimento) : null;
    }
}
