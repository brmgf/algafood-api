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

    private final ConsultaCozinhaService consultaService;
    private final CadastroCozinhaService cadastroService;
    private final CozinhaDTOAssembler assembler;
    private final CozinhaInputDisassembler disassembler;

    @GetMapping
    public List<CozinhaDTO> listar() {
        return assembler.toCollectionDTO(consultaService.listar());
    }

    @GetMapping("/{id}")
    public CozinhaDTO buscar(@PathVariable Long id) {
        return assembler.toDTO(consultaService.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CozinhaDTO salvar(@RequestBody @Valid CozinhaInput input) {
        return assembler.toDTO(cadastroService.salvar(disassembler.toObjectModel(input)));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput input) {
        return assembler.toDTO(cadastroService.atualizar(cozinhaId, disassembler.toObjectModel(input)));
    }

    @DeleteMapping("/{cozinhaId}")
    public void remover(@PathVariable Long cozinhaId) {
        cadastroService.remover(cozinhaId);
    }
}
