package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.PedidoDTOAssembler;
import com.brmgf.algafoodapi.api.assembler.PedidoResumoDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.PedidoInputDisassembler;
import com.brmgf.algafoodapi.api.domain.dto.PedidoDTO;
import com.brmgf.algafoodapi.api.domain.dto.PedidoResumoDTO;
import com.brmgf.algafoodapi.api.domain.input.PedidoInput;
import com.brmgf.algafoodapi.service.cadastro.CadastroPedidoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/pedidos")
@RestController
public class PedidoController {

    private final ConsultaPedidoService consultaService;
    private final CadastroPedidoService cadastroService;
    private final PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
    private final PedidoDTOAssembler pedidoDTOAssembler;
    private final PedidoInputDisassembler disassembler;

    @GetMapping
    public List<PedidoResumoDTO> listar() {
        return pedidoResumoDTOAssembler.toCollectionDTO(consultaService.listar());
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO buscar(@PathVariable Long pedidoId) {
        return pedidoDTOAssembler.toDTO(consultaService.buscar(pedidoId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PedidoDTO salvar(@RequestBody @Valid PedidoInput input) {
        return pedidoDTOAssembler.toDTO(cadastroService.salvar(disassembler.toObjectModel(input)));
    }
}
