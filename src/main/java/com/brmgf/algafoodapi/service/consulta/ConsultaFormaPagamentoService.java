package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.FormaPagamentoNaoEncontradoException;
import com.brmgf.algafoodapi.domain.model.FormaPagamento;
import com.brmgf.algafoodapi.domain.repository.FormaPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaFormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    @Transactional(readOnly = true)
    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public FormaPagamento buscar(Long formaPagamentoId) {
        return formaPagamentoRepository.findById(formaPagamentoId)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradoException(formaPagamentoId));
    }
}
