package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.domain.exception.EntidadeCadastradaException;
import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Estado;
import com.brmgf.algafoodapi.service.CadastroEstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/estados")
@RestController
public class EstadoController {

    private final CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return cadastroEstadoService.listar();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Estado estado = cadastroEstadoService.buscar(estadoId);
        if (nonNull(estado)) {
            return ResponseEntity.ok(estado);
        }

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Estado estado) {
        try {
            Estado estadoCriado = cadastroEstadoService.salvar(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estadoCriado);
        } catch (EntidadeCadastradaException entidadeCadastradaException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(entidadeCadastradaException.getMessage());
        }
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        try {
            Estado estadoAtualizado = cadastroEstadoService.atualizar(estadoId, estado);
            return ResponseEntity.ok(estadoAtualizado);
        } catch (EntidadeCadastradaException entidadeCadastradaException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(entidadeCadastradaException.getMessage());
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId) {
        try {
            cadastroEstadoService.remover(estadoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException entidadeEmUsoException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(entidadeEmUsoException.getMessage());
        }
    }
}
