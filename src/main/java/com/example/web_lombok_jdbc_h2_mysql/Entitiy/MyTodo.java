package com.example.web_lombok_jdbc_h2_mysql.Entitiy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "todo", schema = "dbe")


public class MyTodo {
    private String id;
    private String description;
    private Timestamp created;
    private Timestamp modified;
    private Byte completed;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "modified")
    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    @Basic
    @Column(name = "completed")
    public Byte getCompleted() {
        return completed;
    }

    public void setCompleted(Byte completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTodo myTodo = (MyTodo) o;
        return Objects.equals(id, myTodo.id) &&
                Objects.equals(description, myTodo.description) &&
                Objects.equals(created, myTodo.created) &&
                Objects.equals(modified, myTodo.modified) &&
                Objects.equals(completed, myTodo.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, modified, completed);
    }
}
