package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.domain.model.Cozinha;
import com.brmgf.algafoodapi.service.CadastroCozinhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cozinhas")
@RestController
public class CozinhaController {

    private final CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cadastroCozinhaService.listar();
    }

    @GetMapping("/{id}")
    public Cozinha buscar(@PathVariable Long id) {
        return cadastroCozinhaService.buscar(id);
    }
}
