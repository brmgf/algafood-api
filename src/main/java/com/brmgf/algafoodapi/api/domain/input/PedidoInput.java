package com.brmgf.algafoodapi.api.domain.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PedidoInput {

    @Valid
    @NotNull
    private RestauranteIdInput restaurante;

    @Valid
    @NotNull
    private FormaPagamentoIdInput formaPagamento;

    @Valid
    @NotNull
    private EnderecoInput enderecoEntrega;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInput> itens;
}
