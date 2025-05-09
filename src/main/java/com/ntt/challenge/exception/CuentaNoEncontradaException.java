package com.ntt.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CuentaNoEncontradaException extends RuntimeException {
    public CuentaNoEncontradaException(String message) {
        super(message);
    }
}
