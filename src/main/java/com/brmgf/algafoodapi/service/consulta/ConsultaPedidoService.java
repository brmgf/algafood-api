package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Pedido;
import com.brmgf.algafoodapi.domain.repository.PedidoRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ConsultaPedidoService {

    private static final String PEDIDO = "Pedido";

    private final PedidoRepository repository;

    @Transactional(readOnly = true)
    public Page<Pedido> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Pedido buscar(Long pedidoId) {
        return repository.findById(pedidoId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), PEDIDO, pedidoId)
                ));
    }
}
