package com.brmgf.algafoodapi.util;

import lombok.Getter;

public enum MensagemErro {

    ERRO_REALIZAR_OPERACAO_ENTIDADE_EM_USO("Não foi possível realizar operação. %s de código %d está em uso."),
    ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA("Não foi possível realizar operação. %s de código %d não encontrado(a)."),
    ERRO_REALIZAR_OPERACAO_CAMPO_OBRIGATORIO("Não foi possível realizar operação. %s é um campo obrigatório.");

    @Getter
    private String descricao;

    MensagemErro(String descricao) {
        this.descricao = descricao;
    }
}
