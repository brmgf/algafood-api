package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.domain.model.Estado;
import com.brmgf.algafoodapi.service.cadastro.CadastroEstadoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaEstadoService;
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
@RequestMapping("/estados")
@RestController
public class EstadoController {

    private final ConsultaEstadoService consultaEstadoService;
    private final CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return consultaEstadoService.listar();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return consultaEstadoService.buscar(estadoId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Estado salvar(@RequestBody @Valid Estado estado) {
        return cadastroEstadoService.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody @Valid Estado estado) {
        return cadastroEstadoService.atualizar(estadoId, estado);
    }

    @DeleteMapping("/{estadoId}")
    public void remover(@PathVariable Long estadoId) {
        cadastroEstadoService.remover(estadoId);
    }
}
