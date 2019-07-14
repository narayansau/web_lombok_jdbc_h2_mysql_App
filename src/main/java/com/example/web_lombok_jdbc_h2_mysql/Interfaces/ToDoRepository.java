package com.example.web_lombok_jdbc_h2_mysql.Interfaces;


import com.example.web_lombok_jdbc_h2_mysql.Entitiy.ToDo;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ToDoRepository  implements  CommonRepository <ToDo> {
    @Override
    public ToDo save(ToDo domain) {
        return null;
    }

    @Override
    public Iterable<ToDo> save(Collection<ToDo> domains) {
        return null;
    }

    @Override
    public void delete(ToDo domain) {

    }

    @Override
    public ToDo findById(String id) {
        return null;
    }

    @Override
    public Iterable<ToDo> findAll() {
        return null;
    }
}
