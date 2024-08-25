package com.brmgf.algafoodapi.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

    CRIADO("criado"),
    CONFIRMADO("confirmado", CRIADO),
    ENTREGUE("entregue", CONFIRMADO),
    CANCELADO("cancelado", CRIADO, CONFIRMADO);

    @Getter
    private String descricao;

    private List<StatusPedido> statusAnteriores;

    StatusPedido(String descricao, StatusPedido... status) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(status);
    }

    public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
        return !novoStatus.statusAnteriores.contains(this);
    }
}
