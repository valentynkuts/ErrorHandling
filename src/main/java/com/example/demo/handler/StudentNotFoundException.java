package com.example.demo.handler;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String str){
        super(str);
    }
}
