package by.iba.bank.service;

import java.util.List;

public interface Service <T>{
    T findEntity(Integer id);

    void saveEntity(T entity);

    void deleteEntity(T entity);

    void updateEntity(T entity);

    List<T> findAllEntities();

}
