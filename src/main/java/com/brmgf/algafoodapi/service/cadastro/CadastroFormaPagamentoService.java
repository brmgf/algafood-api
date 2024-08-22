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

    private final FormaPagamentoRepository repository;
    private final ConsultaFormaPagamentoService consultaService;

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    @Transactional
    public FormaPagamento atualizar(Long formaPagamentoId, FormaPagamento novaFormaPagamento) {
        FormaPagamento formaPagamento = consultaService.buscar(formaPagamentoId);

        BeanUtils.copyProperties(novaFormaPagamento, formaPagamento, "id");
        return repository.save(formaPagamento);
    }

    @Transactional
    public void remover(Long formaPagamentoId) {
        try {
            FormaPagamento formaPagamento = consultaService.buscar(formaPagamentoId);

            repository.delete(formaPagamento);
            repository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(
                    String.format(MensagemErro.ENTIDADE_EM_USO.getDescricao(), "Forma de pagamento", formaPagamentoId)
            );
        }
    }

}
