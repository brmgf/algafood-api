package com.brmgf.algafoodapi.domain.exception;

import com.brmgf.algafoodapi.util.MensagemErro;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestauranteNaoEncontradoException extends NegocioException {

    private static final String NOME_ENTIDADE = "Restaurante";

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long restauranteId) {
        this(String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, restauranteId));
    }
}