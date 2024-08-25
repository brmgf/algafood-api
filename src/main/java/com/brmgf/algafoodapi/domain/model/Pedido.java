package com.brmgf.algafoodapi.domain.model;

import com.brmgf.algafoodapi.domain.enums.StatusPedido;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.util.MensagemErro;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Pedido {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal subtotal;

    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal valorTotal;

    @NotNull
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dataHoraCriacao;

    private LocalDateTime dataHoraConfirmacao;

    private LocalDateTime dataHoraCancelamento;

    private LocalDateTime dataHoraEntrega;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Embedded
    private Endereco enderecoEntrega;

    @NotNull
    @Column(nullable = false)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);
        this.subtotal = getItens().stream()
                .map(item -> item.getPrecoTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void confirmar() {
        setStatusFluxo(StatusPedido.CONFIRMADO);
        setDataHoraConfirmacao(LocalDateTime.now());
    }

    public void entregar() {
        setStatusFluxo(StatusPedido.ENTREGUE);
        setDataHoraEntrega(LocalDateTime.now());
    }

    public void cancelar() {
        setStatusFluxo(StatusPedido.CANCELADO);
        setDataHoraCancelamento(LocalDateTime.now());
    }

    private void setStatusFluxo(StatusPedido novoStatus) {
        if (getStatus().naoPodeAlterarPara(novoStatus)) {
            throw new NegocioException(
                    String.format(MensagemErro.STATUS_PEDIDO_NAO_PODE_SER_ALTERADO.getDescricao(),
                            getId(), getStatus().getDescricao(), novoStatus.getDescricao())
            );
        }

        this.status = novoStatus;
    }
}
