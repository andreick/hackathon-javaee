package com.stefanini.dao;

import java.util.List;

public interface CrudDAO<T, I> {

    void save(T t);

    T findById(I id);

    <T> List<T> listAll();

    T update(T t);

    void delete(I id);
}
