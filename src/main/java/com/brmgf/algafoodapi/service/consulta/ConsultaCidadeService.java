package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.CidadeNaoEncontradaException;
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

    private static final String NOME_ENTIDADE = "Cidade";

    private final CidadeRepository cidadeRepository;

    @Transactional(readOnly = true)
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cidade buscar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }

    @Transactional(readOnly = true)
    public Cidade buscarCidadeEndereco(Endereco endereco) {
        if (isNull(endereco.getCidade())) {
            throw new CampoObrigatorioException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_CAMPO_OBRIGATORIO.getDescricao(), NOME_ENTIDADE)
            );
        }

        Long cidadeId = endereco.getCidade().getId();
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }
}
