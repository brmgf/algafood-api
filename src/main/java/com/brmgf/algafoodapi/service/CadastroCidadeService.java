package com.brmgf.algafoodapi.service;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.domain.model.Estado;
import com.brmgf.algafoodapi.domain.repository.CidadeRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class CadastroCidadeService {

    private final String NOME_ENTIDADE = "Cidade";

    private final CidadeRepository cidadeRepository;
    private final CadastroEstadoService cadastroEstadoService;

    @Transactional(readOnly = true)
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cidade buscar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElse(null);
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        cidade.setEstado(cadastroEstadoService.buscarEstadoCidade(cidade));
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Cidade atualizar(Long cidadeId, Cidade novaCidade) {
        Cidade cidade = this.buscar(cidadeId);
        if (isNull(cidade)) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, cidadeId)
            );
        }

        BeanUtils.copyProperties(novaCidade, cidade, "id");
        cidade.setEstado(cadastroEstadoService.buscarEstadoCidade(novaCidade));
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void remover(Long cidadeId) {
        Cidade cidade = this.buscar(cidadeId);
        if (isNull(cidade)) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, cidadeId)
            );
        }

        cidadeRepository.deleteById(cidadeId);
    }
}
