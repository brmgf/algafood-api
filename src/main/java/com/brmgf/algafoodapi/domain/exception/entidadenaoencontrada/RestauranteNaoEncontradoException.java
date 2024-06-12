package com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada;

import com.brmgf.algafoodapi.util.MensagemErro;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final String NOME_ENTIDADE = "Restaurante";

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long restauranteId) {
        this(String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, restauranteId));
    }
}
