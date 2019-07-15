package com.example.web_lombok_jdbc_h2_mysql.Entitiy;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "todo", schema = "dbe")
public class ToDo {

    @NotNull
    @Id
       @GeneratedValue(generator = "system-uuid")
       @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private  String id;

    @NotNull
    @NotBlank
    private  String description;
    @Column (insertable = true , updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean competed;


  @PrePersist
    void onCreate() {
      this.setCreated(LocalDateTime.now());
      this.setModified(LocalDateTime.now());
  }
 @PreUpdate
    void onUpdate() {
     this.setModified(LocalDateTime.now());
 }

    public ToDo(@NotNull @NotBlank String description) {
        this.description = description;
    }

    public ToDo() {
    }

    public ToDo(@NotNull @NotBlank String description, LocalDateTime created, LocalDateTime modified, boolean competed) {
        this.description = description;
        this.created = created;
        this.modified = modified;
        this.competed = competed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public boolean isCompeted() {
        return competed;
    }

    public void setCompeted(boolean competed) {
        this.competed = competed;
    }
}
