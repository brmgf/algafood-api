package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.model.FormaPagamento;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociacaoFormaPagamentoRestauranteService {

    @Transactional
    public void associarFormaPagamentoRestaurante(Restaurante restaurante, FormaPagamento formaPagamento) {
        restaurante.getFormasPagamento().add(formaPagamento);
    }

    @Transactional
    public void desassociarFormaPagamentoRestaurante(Restaurante restaurante, FormaPagamento formaPagamento) {
        restaurante.getFormasPagamento().remove(formaPagamento);
    }
}
