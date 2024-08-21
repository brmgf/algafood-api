package com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada;

import com.brmgf.algafoodapi.util.MensagemErro;

public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final String NOME_ENTIDADE = "Forma pagamento";

    public FormaPagamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoNaoEncontradoException(Long formaPagamentoId) {
        this(String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, formaPagamentoId));
    }
}
