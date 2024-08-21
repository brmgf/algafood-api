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

    private final ConsultaEstadoService consultaEstadoService;
    private final CadastroEstadoService cadastroEstadoService;
    private final EstadoDTOAssember estadoDTOAssember;
    private final EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoDTO> listar() {
        return estadoDTOAssember.toCollectionDTO(consultaEstadoService.listar());
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        return estadoDTOAssember.toDTO(consultaEstadoService.buscar(estadoId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EstadoDTO salvar(@RequestBody @Valid EstadoInput estado) {
        return estadoDTOAssember
                .toDTO(cadastroEstadoService.salvar(estadoInputDisassembler.toObjectModel(estado)));
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estado) {
        return estadoDTOAssember
                .toDTO(cadastroEstadoService.atualizar(estadoId, estadoInputDisassembler.toObjectModel(estado)));
    }

    @DeleteMapping("/{estadoId}")
    public void remover(@PathVariable Long estadoId) {
        cadastroEstadoService.remover(estadoId);
    }
}
