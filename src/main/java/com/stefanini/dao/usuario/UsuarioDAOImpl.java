package com.stefanini.dao.usuario;

import com.stefanini.dao.GenericDAO;
import com.stefanini.model.Usuario;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UsuarioDAOImpl extends GenericDAO<Usuario, Long> implements UsuarioDAO {
}
