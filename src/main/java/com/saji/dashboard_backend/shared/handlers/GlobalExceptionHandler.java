package com.saji.dashboard_backend.shared.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ERROR>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(ex.getClass().getName());
        System.out.println(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
