package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.enums.StatusPedido;
import com.brmgf.algafoodapi.domain.model.Pedido;
import com.brmgf.algafoodapi.domain.model.Produto;
import com.brmgf.algafoodapi.domain.model.Usuario;
import com.brmgf.algafoodapi.domain.repository.PedidoRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaFormaPagamentoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaProdutoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CadastroPedidoService {

    private final ConsultaRestauranteService consultaRestauranteService;
    private final ConsultaFormaPagamentoService consultaFormaPagamentoService;
    private final ConsultaProdutoService consultaProdutoService;
    private final PedidoRepository repository;

    @Transactional
    public Pedido salvar(Pedido pedido) {
        validarPedido(pedido);
        validarProdutos(pedido);
        calcularValores(pedido);

        return repository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        pedido.setRestaurante(consultaRestauranteService.buscar(pedido.getRestaurante().getId()));
        pedido.setFormaPagamento(consultaFormaPagamentoService.buscarFormaPagamentoRestaurante(
                pedido.getRestaurante(),
                pedido.getFormaPagamento().getId()));
        pedido.setCliente(new Usuario(1L));
    }

    private void validarProdutos(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = consultaProdutoService
                    .buscarProdutoRestaurante(pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

    private void calcularValores(Pedido pedido) {
        pedido.setDataHoraCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.CRIADO);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();
    }
}
