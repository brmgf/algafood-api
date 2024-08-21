package com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada;

import com.brmgf.algafoodapi.util.MensagemErro;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final String NOME_ENTIDADE = "Grupo";

    public GrupoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long grupoId) {
        this(String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, grupoId));
    }
}
