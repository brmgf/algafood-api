package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.FormaPagamentoDTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.FormaPagamentoDTO;
import com.brmgf.algafoodapi.domain.model.FormaPagamento;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.service.cadastro.AssociacaoFormaPagamentoRestauranteService;
import com.brmgf.algafoodapi.service.consulta.ConsultaFormaPagamentoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
@RestController
public class RestauranteFormaPagamentoController {

    private final AssociacaoFormaPagamentoRestauranteService service;
    private final ConsultaRestauranteService consultaRestauranteService;
    private final ConsultaFormaPagamentoService consultaFormaPagamentoService;
    private final FormaPagamentoDTOAssembler assembler;

    @GetMapping
    public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
        return assembler.toCollectionDTO(restaurante.getFormasPagamento());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{formaPagamentoId}")
    public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
        FormaPagamento formaPagamento = consultaFormaPagamentoService.buscar(formaPagamentoId);
        service.associarFormaPagamentoRestaurante(restaurante, formaPagamento);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{formaPagamentoId}")
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
        FormaPagamento formaPagamento = consultaFormaPagamentoService
                .buscarFormaPagamentoRestaurante(restaurante, formaPagamentoId);
        service.desassociarFormaPagamentoRestaurante(restaurante, formaPagamento);
    }
}
