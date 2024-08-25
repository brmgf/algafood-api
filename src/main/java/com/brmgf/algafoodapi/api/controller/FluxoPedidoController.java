package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.service.cadastro.FluxoPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/pedidos/{pedidoId}")
@RestController
public class FluxoPedidoController {

    private final FluxoPedidoService service;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/confirmacao")
    public void confirmar(@PathVariable Long pedidoId) {
        service.confirmar(pedidoId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/entrega")
    public void entregar(@PathVariable Long pedidoId) {
        service.entregar(pedidoId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cancelamento")
    public void cancelar(@PathVariable Long pedidoId) {
        service.cancelar(pedidoId);
    }
}
