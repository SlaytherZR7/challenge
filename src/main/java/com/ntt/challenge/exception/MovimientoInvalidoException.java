package com.ntt.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MovimientoInvalidoException extends RuntimeException {
    public MovimientoInvalidoException(String message) {
        super(message);
    }
}
