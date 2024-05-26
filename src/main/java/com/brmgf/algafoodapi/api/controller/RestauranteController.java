package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.service.cadastro.CadastroRestauranteService;
import com.brmgf.algafoodapi.service.consulta.ConsultaRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/restaurantes")
@RestController
public class RestauranteController {

    private final ConsultaRestauranteService consultaRestauranteService;
    private final CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return consultaRestauranteService.listar();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<?> buscar(@PathVariable Long restauranteId) {
        try {
            Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
            return ResponseEntity.ok(restaurante);
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entidadeNaoEncontradaException.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteCriado = cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteCriado);
        } catch (CampoObrigatorioException campoObrigatorioException) {
            return ResponseEntity.badRequest().body(campoObrigatorioException.getMessage());
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.badRequest().body(entidadeNaoEncontradaException.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteAtualizado = cadastroRestauranteService.atualizar(restauranteId, restaurante);
            return ResponseEntity.ok(restauranteAtualizado);
        } catch (CampoObrigatorioException campoObrigatorioException) {
            return ResponseEntity.badRequest().body(campoObrigatorioException.getMessage());
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entidadeNaoEncontradaException.getMessage());
        }
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<?> remover(@PathVariable Long restauranteId) {
        try {
            cadastroRestauranteService.remover(restauranteId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entidadeNaoEncontradaException.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
        try {
            Restaurante restauranteAtualizado = cadastroRestauranteService.atualizarDadosParcialmente(restauranteId, campos);
            return ResponseEntity.ok(restauranteAtualizado);
        } catch (CampoObrigatorioException campoObrigatorioException) {
            return ResponseEntity.badRequest().body(campoObrigatorioException.getMessage());
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entidadeNaoEncontradaException.getMessage());
        }
    }
}
