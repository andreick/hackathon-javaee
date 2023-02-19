package com.stefanini.dao;

import com.stefanini.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UsuarioDAO extends GenericDAO<Usuario, Long> {

    @Transactional
    public void delete(Usuario usuario) {
        var usuarioManaged = em.merge(usuario);
        em.remove(usuarioManaged);
    }

    public List<String> listEmailProviders() {
        String jpql = "SELECT DISTINCT(SUBSTRING(u.email, LOCATE('@', u.email) + 1, LENGTH(u.email))) FROM Usuario u";
        return em.createQuery(jpql, String.class).getResultList();
    }
}
