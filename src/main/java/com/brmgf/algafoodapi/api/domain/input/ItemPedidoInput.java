package com.brmgf.algafoodapi.api.domain.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoInput {

    @Valid
    @NotNull
    private ProdutoIdInput produto;

    @Positive
    private Integer quantidade;

    private String observacao;
}
