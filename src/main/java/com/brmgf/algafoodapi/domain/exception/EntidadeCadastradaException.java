package com.brmgf.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeCadastradaException extends RuntimeException {

    public EntidadeCadastradaException(String message) {
        super(message);
    }
}
