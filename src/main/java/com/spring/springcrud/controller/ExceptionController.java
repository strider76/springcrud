package com.spring.springcrud.controller;

import com.spring.springcrud.dto.ErrorDTO;
import com.spring.springcrud.exceptions.EmployeeNotfoundException;
import com.spring.springcrud.exceptions.ErrorDeletingEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.spring.springcrud.controller")
public class ExceptionController {

    @ExceptionHandler(EmployeeNotfoundException.class)
    public ResponseEntity<ErrorDTO> idNotFoundError (Exception e) {
        return new ResponseEntity<>( new ErrorDTO("12",e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorDeletingEmployeeException.class)
    public ResponseEntity<ErrorDTO> getDeleting(Exception e) {
        return new ResponseEntity<>( new ErrorDTO("11",e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> getError(Exception e) {
        return new ResponseEntity<>( new ErrorDTO("10",e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
