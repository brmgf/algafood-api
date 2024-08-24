package com.brmgf.algafoodapi.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PedidoDTO {

    private Long id;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraConfirmacao;
    private LocalDateTime dataHoraCancelamento;
    private LocalDateTime dataHoraEntrega;
    private NomeRestauranteDTO restaurante;
    private UsuarioDTO cliente;
    private EnderecoDTO enderecoEntrega;
    private List<ItemPedidoDTO> itens = new ArrayList<>();
    private FormaPagamentoDTO formaPagamento;
}
