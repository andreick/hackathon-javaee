package com.stefanini.model;

import com.stefanini.dto.usuario.UsuarioCreateDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "login", nullable = false, length = 20, unique = true)
    private String login;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false, length = 10)
    private String senha;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String login, String email, String senha, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public Usuario(UsuarioCreateDTO dto) {
        this(null, dto.getNome(), dto.getLogin(), dto.getEmail(), dto.getSenha(), dto.getDataNascimento());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(login, usuario.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nome = " + nome + ", " +
                "login = " + login + ", " +
                "email = " + email + ", " +
                "senha = " + senha + ", " +
                "dataDeNascimento = " + dataNascimento + ")";
    }
}
