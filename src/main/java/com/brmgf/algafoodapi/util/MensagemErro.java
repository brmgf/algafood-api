package com.brmgf.algafoodapi.util;

import lombok.Getter;

public enum MensagemErro {

    ENTIDADE_EM_USO("Não foi possível realizar operação. %s de código %d está em uso."),
    ENTIDADE_NAO_ENCONTRADA("Não foi possível realizar operação. %s de código %d não encontrado(a)."),
    CAMPO_OBRIGATORIO("Não foi possível realizar operação. %s é um campo obrigatório."),
    ENTIDADE_JA_CADASTRADA("Não foi possível realizar operação. Este(a) %s já está cadastrado(a) no sistema."),
    SENHA_ATUAL_DIFERENTE_SENHA_CADASTRADA("A senha atual não coincide com a senha cadastrada do usuário."),
    USUARIO_CADASTRADO_EMAIL("Já existe um usuário cadastrado com o e-mail.");

    @Getter
    private String descricao;

    MensagemErro(String descricao) {
        this.descricao = descricao;
    }
}
