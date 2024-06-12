package com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada;

import com.brmgf.algafoodapi.domain.exception.NegocioException;

public abstract class EntidadeNaoEncontradaException extends NegocioException {

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
