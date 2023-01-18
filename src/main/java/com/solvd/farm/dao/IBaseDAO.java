package com.solvd.farm.dao;

public interface IBaseDAO<T> {
    T getEntityById(long id);

    void updateEntity(T entity);

    T createEntity(T entity);

    void removeEntity(long id);
}
