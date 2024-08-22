package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.EstadoDTOAssember;
import com.brmgf.algafoodapi.api.disassembler.EstadoInputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.EstadoInput;
import com.brmgf.algafoodapi.api.domain.dto.EstadoDTO;
import com.brmgf.algafoodapi.service.cadastro.CadastroEstadoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaEstadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/estados")
@RestController
public class EstadoController {

    private final ConsultaEstadoService consultaService;
    private final CadastroEstadoService cadastroService;
    private final EstadoDTOAssember assember;
    private final EstadoInputDisassembler disassembler;

    @GetMapping
    public List<EstadoDTO> listar() {
        return assember.toCollectionDTO(consultaService.listar());
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        return assember.toDTO(consultaService.buscar(estadoId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EstadoDTO salvar(@RequestBody @Valid EstadoInput input) {
        return assember.toDTO(cadastroService.salvar(disassembler.toObjectModel(input)));
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput input) {
        return assember.toDTO(cadastroService.atualizar(estadoId, disassembler.toObjectModel(input)));
    }

    @DeleteMapping("/{estadoId}")
    public void remover(@PathVariable Long estadoId) {
        cadastroService.remover(estadoId);
    }
}
