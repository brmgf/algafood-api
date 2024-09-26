package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.domain.filter.VendaDiariaFilter;
import com.brmgf.algafoodapi.domain.model.dto.VendaDiariaDTO;
import com.brmgf.algafoodapi.service.VendaDiariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    private final VendaDiariaService vendaDiariaService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiariaDTO> consultarVendasDiarias(VendaDiariaFilter filter) {
        return vendaDiariaService.consultarVendasDiarias(filter);
    }
}
