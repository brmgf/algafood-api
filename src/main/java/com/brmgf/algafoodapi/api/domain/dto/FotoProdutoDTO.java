package com.brmgf.algafoodapi.api.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoDTO {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
