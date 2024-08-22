package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.domain.model.Endereco;
import com.brmgf.algafoodapi.domain.repository.CidadeRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class ConsultaCidadeService {

    private static final String CIDADE = "Cidade";

    private final CidadeRepository repository;

    @Transactional(readOnly = true)
    public List<Cidade> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Cidade buscar(Long cidadeId) {
        return repository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), CIDADE, cidadeId)
                ));
    }

    @Transactional(readOnly = true)
    public Cidade buscarCidadeEndereco(Endereco endereco) {
        if (isNull(endereco.getCidade())) {
            throw new CampoObrigatorioException(
                    String.format(MensagemErro.CAMPO_OBRIGATORIO.getDescricao(), CIDADE)
            );
        }

        Long cidadeId = endereco.getCidade().getId();
        return repository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), CIDADE, cidadeId)
                ));
    }
}
