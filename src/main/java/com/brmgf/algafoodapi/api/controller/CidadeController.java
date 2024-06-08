package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.service.cadastro.CadastroCidadeService;
import com.brmgf.algafoodapi.service.consulta.ConsultaCidadeService;
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

    @GetMapping
    public List<Cidade> listar() {
        return consultaCidadeService.listar();
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {
        return consultaCidadeService.buscar(cidadeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cidade salvar(@RequestBody Cidade novaCidade) {
        return cadastroCidadeService.salvar(novaCidade);
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade novaCidade) {
        return cadastroCidadeService.atualizar(cidadeId, novaCidade);
    }

    @DeleteMapping("/{cidadeId}")
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidadeService.remover(cidadeId);
    }
}
