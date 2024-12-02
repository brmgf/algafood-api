package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.CidadeDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.CidadeInputDisassembler;
import com.brmgf.algafoodapi.api.domain.dto.CidadeDTO;
import com.brmgf.algafoodapi.api.domain.input.CidadeInput;
import com.brmgf.algafoodapi.service.cadastro.CadastroCidadeService;
import com.brmgf.algafoodapi.service.consulta.ConsultaCidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CidadeController implements CidadeOpenApi {

    private final ConsultaCidadeService consultaService;
    private final CadastroCidadeService cadastroService;
    private final CidadeDTOAssembler assembler;
    private final CidadeInputDisassembler disassembler;

    @GetMapping
    public List<CidadeDTO> listar() {
        return assembler.toCollectionDTO(consultaService.listar());
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        return assembler.toDTO(consultaService.buscar(cidadeId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CidadeDTO salvar(@RequestBody @Valid CidadeInput input) {
        return assembler.toDTO(cadastroService.salvar(disassembler.toObjectModel(input)));
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput input) {
        return assembler.toDTO(cadastroService.atualizar(cidadeId, disassembler.toObjectModel(input)));
    }

    @DeleteMapping("/{cidadeId}")
    public void remover(@PathVariable Long cidadeId) {
        cadastroService.remover(cidadeId);
    }
}
