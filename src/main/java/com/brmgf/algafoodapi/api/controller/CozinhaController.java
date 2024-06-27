package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.domain.model.Cozinha;
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

    @GetMapping
    public List<Cozinha> listar() {
        return consultaCozinhaService.listar();
    }

    @GetMapping("/{id}")
    public Cozinha buscar(@PathVariable Long id) {
        return consultaCozinhaService.buscar(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cozinha salvar(@RequestBody @Valid Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid Cozinha cozinha) {
        return cadastroCozinhaService.atualizar(cozinhaId, cozinha);
    }

    @DeleteMapping("/{cozinhaId}")
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.remover(cozinhaId);
    }
}
