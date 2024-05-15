package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.service.CadastroCidadeService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@RequestMapping("/cidades")
@RestController
public class CidadeController {

    private final CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cadastroCidadeService.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidadeService.buscar(cidadeId);
        if (isNull(cidade)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade novaCidade) {
        try {
            Cidade cidade = cadastroCidadeService.salvar(novaCidade);
            return ResponseEntity.ok(cidade);
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entidadeNaoEncontradaException.getMessage());
        } catch (CampoObrigatorioException campoObrigatorioException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(campoObrigatorioException.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade novaCidade) {
        try {
            Cidade cidade = cadastroCidadeService.atualizar(cidadeId, novaCidade);
            return ResponseEntity.ok(cidade);
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entidadeNaoEncontradaException.getMessage());
        } catch (CampoObrigatorioException campoObrigatorioException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(campoObrigatorioException.getMessage());
        }
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
        try {
            cadastroCidadeService.remover(cidadeId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.notFound().build();
        }
    }
}
