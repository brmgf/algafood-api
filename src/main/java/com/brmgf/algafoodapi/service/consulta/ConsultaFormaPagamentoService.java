package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.FormaPagamento;
import com.brmgf.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaFormaPagamentoService {

    private static final String FORMA_PAGAMENTO = "Forma de pagamento";

    private final FormaPagamentoRepository repository;

    @Transactional(readOnly = true)
    public List<FormaPagamento> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public FormaPagamento buscar(Long formaPagamentoId) {
        return repository.findById(formaPagamentoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), FORMA_PAGAMENTO, formaPagamentoId)
                ));
    }
}
