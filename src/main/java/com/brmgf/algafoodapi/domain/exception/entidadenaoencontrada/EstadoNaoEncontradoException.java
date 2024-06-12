package com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada;

import com.brmgf.algafoodapi.util.MensagemErro;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final String NOME_ENTIDADE = "Estado";

    public EstadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long estadoId) {
        this(String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, estadoId));
    }
}
