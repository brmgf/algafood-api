package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import com.brmgf.algafoodapi.service.CadastroCozinhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import static java.util.Objects.nonNull;

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
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = cadastroCozinhaService.buscar(id);
        if (nonNull(cozinha)) {
            return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cozinha salvar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        try {
            Cozinha cozinhaAtualizada = cadastroCozinhaService.atualizar(cozinhaId, cozinha);
            return ResponseEntity.ok(cozinhaAtualizada);
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
        try {
            cadastroCozinhaService.remover(cozinhaId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException entidadeEmUsoException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(entidadeEmUsoException.getMessage());
        }
    }
}
