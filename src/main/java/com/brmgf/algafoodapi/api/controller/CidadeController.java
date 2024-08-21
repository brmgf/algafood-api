package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.CidadeDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.CidadeInputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.CidadeInput;
import com.brmgf.algafoodapi.api.domain.dto.CidadeDTO;
import com.brmgf.algafoodapi.service.cadastro.CadastroCidadeService;
import com.brmgf.algafoodapi.service.consulta.ConsultaCidadeService;
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
@RequestMapping("/cidades")
@RestController
public class CidadeController {

    private final ConsultaCidadeService consultaCidadeService;
    private final CadastroCidadeService cadastroCidadeService;
    private final CidadeDTOAssembler cidadeDTOAssembler;
    private final CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public List<CidadeDTO> listar() {
        return cidadeDTOAssembler.toCollectionDTO(consultaCidadeService.listar());
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        return cidadeDTOAssembler.toDTO(consultaCidadeService.buscar(cidadeId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CidadeDTO salvar(@RequestBody @Valid CidadeInput novaCidade) {
        return cidadeDTOAssembler
                .toDTO(cadastroCidadeService.salvar(cidadeInputDisassembler.toObjectModel(novaCidade)));
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput novaCidade) {
        return cidadeDTOAssembler
                .toDTO(cadastroCidadeService.atualizar(cidadeId, cidadeInputDisassembler.toObjectModel(novaCidade)));
    }

    @DeleteMapping("/{cidadeId}")
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidadeService.remover(cidadeId);
    }
}
