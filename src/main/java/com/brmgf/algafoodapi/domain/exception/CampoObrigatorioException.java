package com.brmgf.algafoodapi.domain.exception;

public class CampoObrigatorioException extends RuntimeException {

    public CampoObrigatorioException(String mensagem) {
        super(mensagem);
    }
}
