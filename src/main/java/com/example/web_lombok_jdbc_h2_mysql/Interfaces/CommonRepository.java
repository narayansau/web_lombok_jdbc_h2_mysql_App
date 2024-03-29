package com.example.web_lombok_jdbc_h2_mysql.Interfaces;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommonRepository<T>   {



    public T save(T domain);
    public  Iterable<T> save(Collection<T> domains);
    public void delete(T domain);
    public T findById(String id);
    public  Iterable <T> findAll();

}
