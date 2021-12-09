package com.guap.psis.lr1.controoler.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Value;


@Getter
public class ErrorCode {

    private final String message;

    @JsonCreator
    public ErrorCode(String message) {
        this.message = message;
    }
}
