package com.brmgf.algafoodapi.domain.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class VendaDiariaFilter {

    private Long restauranteId;
    private LocalDateTime dataCriacaoInicio;
    private LocalDateTime dataCriacaoFim;
}
