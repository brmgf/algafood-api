package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.domain.repository.CidadeRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaCidadeService;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CadastroCidadeService {

    private final CidadeRepository repository;
    private final ConsultaCidadeService consultaService;
    private final CadastroEstadoService cadastroService;

    @Transactional
    public Cidade salvar(Cidade cidade) {
        try {
            cidade.setEstado(cadastroService.buscarEstadoCidade(cidade));
            return repository.save(cidade);
        } catch (EntidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Transactional
    public Cidade atualizar(Long cidadeId, Cidade novaCidade) {
        Cidade cidade = consultaService.buscar(cidadeId);

        BeanUtils.copyProperties(novaCidade, cidade, "id");

        try {
            cidade.setEstado(cadastroService.buscarEstadoCidade(novaCidade));
            return repository.save(cidade);
        } catch (EntidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Transactional
    public void remover(Long cidadeId) {
        try {
            Cidade cidade = consultaService.buscar(cidadeId);

            repository.delete(cidade);
            repository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntidadeEmUsoException(
                    String.format(MensagemErro.ENTIDADE_EM_USO.getDescricao(), "Cidade", cidadeId)
            );
        }
    }
}
