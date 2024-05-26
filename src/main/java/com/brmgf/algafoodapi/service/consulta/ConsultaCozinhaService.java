package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import com.brmgf.algafoodapi.domain.repository.CozinhaRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaCozinhaService {

    private static final String NOME_ENTIDADE = "Cozinha";

    private final CozinhaRepository cozinhaRepository;

    @Transactional(readOnly = true)
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cozinha buscar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, cozinhaId)
                ));
    }
}
