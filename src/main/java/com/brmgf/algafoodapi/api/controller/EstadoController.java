package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.domain.model.Estado;
import com.brmgf.algafoodapi.service.ConsultaEstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/estados")
@RestController
public class EstadoController {

    private final ConsultaEstadoService consultaEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return consultaEstadoService.listar();
    }
}
