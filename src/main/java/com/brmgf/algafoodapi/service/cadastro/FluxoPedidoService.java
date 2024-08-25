package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.model.Pedido;
import com.brmgf.algafoodapi.service.consulta.ConsultaPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FluxoPedidoService {

    private final ConsultaPedidoService consultaPedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = consultaPedidoService.buscar(pedidoId);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(Long pedidoId) {
        Pedido pedido = consultaPedidoService.buscar(pedidoId);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        Pedido pedido = consultaPedidoService.buscar(pedidoId);
        pedido.cancelar();
    }
}
