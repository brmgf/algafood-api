package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.model.FormaPagamento;
import com.brmgf.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaFormaPagamentoService;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CadastroFormaPagamentoService {

    private static final String NOME_ENTIDADE = "Forma pagamento";

    private final FormaPagamentoRepository formaPagamentoRepository;
    private final ConsultaFormaPagamentoService consultaFormaPagamentoService;

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public FormaPagamento atualizar(Long formaPagamentoId, FormaPagamento novaFormaPagamento) {
        FormaPagamento formaPagamento = consultaFormaPagamentoService.buscar(formaPagamentoId);

        BeanUtils.copyProperties(novaFormaPagamento, formaPagamento, "id");
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void remover(Long formaPagamentoId) {
        try {
            FormaPagamento formaPagamento = consultaFormaPagamentoService.buscar(formaPagamentoId);

            formaPagamentoRepository.delete(formaPagamento);
            formaPagamentoRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_EM_USO.getDescricao(), NOME_ENTIDADE, formaPagamentoId)
            );
        }
    }

}
