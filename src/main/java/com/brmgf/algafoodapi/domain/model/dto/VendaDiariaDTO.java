package com.brmgf.algafoodapi.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class VendaDiariaDTO {

    private LocalDate data;
    private Long totalVendas;
    private BigDecimal totalFaturado;
}
