package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<Map<String,String>> hanleInvallidOrder(InvalidOrderException ex){
      Map<String,String> error= new HashMap<>();
      error.put("error",ex.getMessage());
      return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}
