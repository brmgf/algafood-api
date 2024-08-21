package com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada;

import com.brmgf.algafoodapi.util.MensagemErro;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final String NOME_ENTIDADE = "Usuário";

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long usuarioId) {
        this(String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, usuarioId));
    }
}
