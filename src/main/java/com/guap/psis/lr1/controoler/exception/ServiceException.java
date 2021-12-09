package com.guap.psis.lr1.controoler.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

    private final HttpStatus status;

    public ServiceException(HttpStatus status) {
        this.status = status;
    }

    public ServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }

    public ServiceException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }
}
