package com.example.web_lombok_jdbc_h2_mysql.Interfaces;


import com.example.web_lombok_jdbc_h2_mysql.Entitiy.ToDo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ToDoRepository  implements  CommonRepository <ToDo> {



    private    final  static String SQL_INSERT = "INSERT INTO dbe.todo " +
            "  (id,description,created,modified,completed) VALUES " +
            " (:id ,:description ,:created ,:modified ,:completed )";
    private static final String SQL_QUERY_FIND_ALL = " SELECT todo.id, todo.description, todo.created, todo.modified, todo.completed FROM dbe.todo" ;

    private static final String SQL_QUERY_FIND_BY_ID = SQL_QUERY_FIND_ALL + " where id = :id";

    private static final String SQL_UPDATE =
            "UPDATE dbe.todo\n" +
                    "SET\n" +
                    "id = id: ,\n" +
                    "description = :description ,\n" +
                    "created = :created ,\n" +
                    "modified = :modified ,\n" +
                    "completed = :completed \n" +
                    "WHERE id = :id" ;

    private static final String SQL_DELETE =
            "DELETE FROM `dbe`.`todo`\n" +
                    "WHERE  id = :id;\n";

    private final NamedParameterJdbcTemplate  jdbcTemplate;

    public ToDoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<ToDo> toDoRowMapper = (ResultSet rs , int rownum) ->
    {
        ToDo  toDo = new ToDo();
        toDo.setId(rs.getNString("id"));
        toDo.setDescription(rs.getString("description"));
        toDo.setCreated((rs.getTimestamp("created").toLocalDateTime()));
        toDo.setModified((rs.getTimestamp("modified").toLocalDateTime()));
        toDo.setCompeted(rs.getBoolean("completed"));

        return toDo;
    };

    @Override
    public ToDo save(final ToDo domain) {
        ToDo result = findById(domain.getId());
        if ( result != null ) {
            result.setDescription(domain.getDescription());
            result.setCompeted(domain.isCompeted());
            result.setModified(LocalDateTime.now());
            result.setCreated(LocalDateTime.now());
            return upsert(result,SQL_UPDATE);
        }

        return upsert(result,SQL_INSERT);
    }
           private ToDo upsert ( final ToDo toDo , final String sql ) {
               Map<String , Object>  namedParameters = new HashMap<>();
               namedParameters.put("id" , toDo.getId());
               namedParameters.put("description" , toDo.getDescription());
               namedParameters.put("created" , toDo.getCreated());
               namedParameters.put("modified", toDo.getModified());
               namedParameters.put("completed", toDo.isCompeted());
               this.jdbcTemplate.update(sql, namedParameters);


               return findById(toDo.getId());



           }
    @Override
    public Iterable<ToDo> save(Collection<ToDo> domains) {

        domains.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(ToDo domain) {
        Map<String, String> namedParameters = Collections.
                singletonMap("id", domain.getId());
        this.jdbcTemplate.update(SQL_DELETE,namedParameters);
    }

    @Override
    public ToDo findById(String id) {
        try {
            Map<String, String> namedParameters = Collections.
                    singletonMap("id", id);
            return this.jdbcTemplate.queryForObject(SQL_QUERY_FIND_BY_ID,
                    namedParameters, toDoRowMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;


        }

    }

    @Override
    public Iterable<ToDo> findAll() {
        return this.jdbcTemplate.query(SQL_QUERY_FIND_ALL, toDoRowMapper);
    }
}
