package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.CozinhaDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.CozinhaInputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.CozinhaInput;
import com.brmgf.algafoodapi.api.domain.dto.CozinhaDTO;
import com.brmgf.algafoodapi.service.cadastro.CadastroCozinhaService;
import com.brmgf.algafoodapi.service.consulta.ConsultaCozinhaService;
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
@RequestMapping("/cozinhas")
@RestController
public class CozinhaController {

    private final ConsultaCozinhaService consultaCozinhaService;
    private final CadastroCozinhaService cadastroCozinhaService;
    private final CozinhaDTOAssembler cozinhaDTOAssembler;
    private final CozinhaInputDisassembler cozinhaInputDisassembler;

    @GetMapping
    public List<CozinhaDTO> listar() {
        return cozinhaDTOAssembler.toCollectionDTO(consultaCozinhaService.listar());
    }

    @GetMapping("/{id}")
    public CozinhaDTO buscar(@PathVariable Long id) {
        return cozinhaDTOAssembler.toDTO(consultaCozinhaService.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CozinhaDTO salvar(@RequestBody @Valid CozinhaInput cozinha) {
        return cozinhaDTOAssembler
                .toDTO(cadastroCozinhaService.salvar(cozinhaInputDisassembler.toObjectModel(cozinha)));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinha) {
        return cozinhaDTOAssembler
                .toDTO(cadastroCozinhaService.atualizar(cozinhaId, cozinhaInputDisassembler.toObjectModel(cozinha)));
    }

    @DeleteMapping("/{cozinhaId}")
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.remover(cozinhaId);
    }
}
