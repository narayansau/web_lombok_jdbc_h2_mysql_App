package com.example.web_lombok_jdbc_h2_mysql.Validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ToDoValidationErrorBuilder {

    public static ToDoValidationError
                  fromBindingErrors ( Errors errors) {

        ToDoValidationError error = new
                ToDoValidationError ( "Validation Failed "
                    +  errors.getErrorCount() + "  error(s)");
        for (ObjectError objectError : errors.getAllErrors()){
            error.addValidationError(objectError.getDefaultMessage());

        }
         return error;
    }
}
