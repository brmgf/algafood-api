package com.brmgf.algafoodapi.util;

import lombok.Getter;

public enum MensagemErro {

    ENTIDADE_EM_USO("Não foi possível realizar operação. %s de código %d está em uso."),
    ENTIDADE_NAO_ENCONTRADA("Não foi possível realizar operação. %s de código %d não encontrado(a)."),
    CAMPO_OBRIGATORIO("Não foi possível realizar operação. %s é um campo obrigatório."),
    ENTIDADE_JA_CADASTRADA("Não foi possível realizar operação. Este(a) %s já está cadastrado(a) no sistema."),
    SENHA_ATUAL_DIFERENTE_SENHA_CADASTRADA("A senha atual não coincide com a senha cadastrada do usuário."),
    USUARIO_CADASTRADO_EMAIL("Já existe um usuário cadastrado com o e-mail."),
    ENTIDADE_NAO_POSSUI_VINCULO("%s informado(a) não possui vínculo com o(a) %s."),
    STATUS_PEDIDO_NAO_PODE_SER_ALTERADO("Status do pedido %d não pode ser alterado de %s para %s.");

    @Getter
    private String descricao;

    MensagemErro(String descricao) {
        this.descricao = descricao;
    }
}
