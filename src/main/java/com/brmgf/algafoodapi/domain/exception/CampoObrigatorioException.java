package com.brmgf.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CampoObrigatorioException extends RuntimeException {

    public CampoObrigatorioException(String mensagem) {
        super(mensagem);
    }
}
