package com.example.web_lombok_jdbc_h2_mysql.Interfaces;


import com.example.web_lombok_jdbc_h2_mysql.Entitiy.ToDo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Service
@Repository
public interface ToDoRepository extends
        CrudRepository<ToDo,String> {}