package com.example.web_lombok_jdbc_h2_mysql.Entitiy;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ToDo {

    @NotNull
    private  String id;

    @NotNull
    @NotBlank
    private  String description;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean competed;

    public ToDo() {
        LocalDateTime date =  LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created=this.modified=date;
    }

    public ToDo(@NotNull @NotBlank String description) {
        this();
        this.description = description;
    }
}
