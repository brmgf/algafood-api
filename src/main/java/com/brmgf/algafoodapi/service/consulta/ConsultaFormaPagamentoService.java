package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.FormaPagamento;
import com.brmgf.algafoodapi.domain.model.Restaurante;
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
    private static final String RESTAURANTE = "restaurante";

    private final FormaPagamentoRepository repository;

    @Transactional(readOnly = true)
    public FormaPagamento buscar(Long formaPagamentoId) {
        return repository.findById(formaPagamentoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), FORMA_PAGAMENTO, formaPagamentoId)
                ));
    }

    @Transactional(readOnly = true)
    public FormaPagamento buscarFormaPagamentoRestaurante(Restaurante restaurante, Long formaPagamentoId) {
        FormaPagamento formaPagamentoCadastrada = repository.findById(formaPagamentoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), FORMA_PAGAMENTO, formaPagamentoId)
                ));

        FormaPagamento formaPagamentoRestaurante = restaurante.getFormasPagamento().stream()
                .filter(fp -> formaPagamentoCadastrada.getId().equals(fp.getId()))
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_POSSUI_VINCULO.getDescricao(),
                                FORMA_PAGAMENTO, formaPagamentoId, RESTAURANTE, formaPagamentoId)
                ));

        return formaPagamentoRestaurante;
    }
}
