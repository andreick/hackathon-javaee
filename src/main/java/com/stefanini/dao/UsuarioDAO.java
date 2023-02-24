package com.stefanini.dao;

import com.stefanini.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsuarioDAO extends GenericDAO<Usuario, Long> {

    public List<Usuario> listBirthDayPersonsByMonth(int month) {
        String jpql = "SELECT u FROM Usuario u WHERE MONTH(u.dataNascimento) = :month";
        return createQuery(jpql).setParameter("month", month).getResultList();
    }

    public List<String> listEmailProviders() {
        String jpql = "SELECT DISTINCT(SUBSTRING(u.email, LOCATE('@', u.email) + 1)) FROM Usuario u";
        return em.createQuery(jpql, String.class).getResultList();
    }

    public List<Usuario> listByNameLike(String pattern) {
        String jpql = "SELECT u FROM Usuario u WHERE u.nome like :pattern";
        return createQuery(jpql).setParameter("pattern", pattern).getResultList();
    }
}
