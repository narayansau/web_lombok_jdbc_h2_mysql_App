package com.example.web_lombok_jdbc_h2_mysql.RestController;


import com.example.web_lombok_jdbc_h2_mysql.Entitiy.ToDo;
//import com.example.web_lombok_jdbc_h2_mysql.Interfaces.CommonRepository;
import com.example.web_lombok_jdbc_h2_mysql.Interfaces.ToDoRepository;
import com.example.web_lombok_jdbc_h2_mysql.Validation.ToDoValidationError;
import com.example.web_lombok_jdbc_h2_mysql.Validation.ToDoValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ToDoController{

    private ToDoRepository toDorepository;

    @Autowired
    public ToDoController(ToDoRepository toDorepository) {
        this.toDorepository =  toDorepository;
    }

    @GetMapping("/todo")
    ResponseEntity< Iterable<ToDo>> getToDos () {

        return  ResponseEntity.ok(toDorepository.findAll());
    }

    @GetMapping("/todo/{id}")
      public ResponseEntity<ToDo> getToDoById(@PathVariable String id) {

        Optional<ToDo> toDo = toDorepository.findById(id);
        if ( !toDo.isPresent()) return   ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDo.get());

    }

    @PatchMapping ("/todo/{id}")
    public ResponseEntity<ToDo> setCompleted (
            @PathVariable String id ) {
        Optional<ToDo> toDo = toDorepository.findById(id);
        if(!toDo.isPresent())
            return ResponseEntity.notFound().build();
        ToDo result = toDo.get();
        result.setCompeted(true);
        toDorepository.save(result);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.ok().header("Location",location.toString()).
                build();

    }

    @RequestMapping(value="/todo", method = {
            RequestMethod.POST,
            RequestMethod.PUT})
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo,
                                        Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().
                    body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }
        ToDo result = toDorepository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/todo")
    public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo){
        toDorepository.delete(toDo);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }
}
