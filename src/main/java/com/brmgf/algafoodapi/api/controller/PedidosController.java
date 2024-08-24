package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.PedidoDTOAssembler;
import com.brmgf.algafoodapi.api.assembler.PedidoResumoDTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.PedidoDTO;
import com.brmgf.algafoodapi.api.domain.dto.PedidoResumoDTO;
import com.brmgf.algafoodapi.service.consulta.ConsultaPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/pedidos")
@RestController
public class PedidosController {

    private final ConsultaPedidoService consultaService;
    private final PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
    private final PedidoDTOAssembler pedidoDTOAssembler;

    @GetMapping
    public List<PedidoResumoDTO> listar() {
        return pedidoResumoDTOAssembler.toCollectionDTO(consultaService.listar());
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO buscar(@PathVariable Long pedidoId) {
        return pedidoDTOAssembler.toDTO(consultaService.buscar(pedidoId));
    }
}
