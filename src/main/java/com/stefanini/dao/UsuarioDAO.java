package com.stefanini.dao;

import com.stefanini.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class UsuarioDAO extends GenericDAO<Usuario, Long> {

    @Transactional
    public void delete(Usuario usuario) {
        var usuarioManaged = em.merge(usuario);
        em.remove(usuarioManaged);
    }
}
