package com.guap.psis.lr1.controoler.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorCode> handleConflict(ServiceException ex) {
        return new ResponseEntity<>(new ErrorCode(ex.getMessage()), ex.getStatus());
    }
}
