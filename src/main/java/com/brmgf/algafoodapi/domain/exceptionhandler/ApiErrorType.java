package com.brmgf.algafoodapi.domain.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {

    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada"),
    CAMPO_OBRIGATORIO("Campo obrigatório", "/campo-obrigatorio"),
    ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
    ENTIDADE_CADASTRADA("Entidade cadastrada", "/entidade-cadastrada"),
    REGRA_NEGOCIO("Regra de negócio", "/regra-negocio"),
    MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "/mensagem-incompreensivel"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ERRO_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");

    private String title;
    private String uri;

    ApiErrorType(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }
}
