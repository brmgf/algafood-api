package com.brmgf.algafoodapi.domain.model;

import com.brmgf.algafoodapi.domain.enums.StatusPedido;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;
}
