package com.brmgf.algafoodapi.api.domain.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProdutoInput {

    @NotBlank
    private String nome;

    private String descricao;

    @PositiveOrZero
    private BigDecimal preco;

    @NotNull
    private Boolean ativo;
}
