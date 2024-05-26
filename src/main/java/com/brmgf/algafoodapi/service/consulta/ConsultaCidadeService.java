package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.domain.repository.CidadeRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaCidadeService {

    private final String NOME_ENTIDADE = "Cidade";

    private final CidadeRepository cidadeRepository;

    @Transactional(readOnly = true)
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cidade buscar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, cidadeId)
                ));
    }
}
