package com.example.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
Utwórz klasę StudentExceptionHandler – ma dziedziczyć ona po ResponseEntityExceptionHandler
Utwórz własny typ wyjątku – StudentNotFoundException, który dziedziczy po RuntimeException
Spraw, aby StudentExceptionHandler przechwytywał StudentNotFoundException i zwracał ResponseEntity ze statusem 404.
Zastosuj StudentNotFoundException w metodach StudentController w przypadkach gdy student nie zostałby odnaleziony

 */
@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<Object> handler(RuntimeException ex, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}