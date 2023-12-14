package by.iba.bank.repository;

import java.util.List;

public interface Repository<T> {

    T findById(Integer id);
    List<T> findAll(Class<T> type);
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);



   // List<T> query(String sqlString, Parameter parameter) throws RepositoryException;

   // Optional<T> queryForSingleResult(String sqlString, Parameter parameter) throws RepositoryException;



}
