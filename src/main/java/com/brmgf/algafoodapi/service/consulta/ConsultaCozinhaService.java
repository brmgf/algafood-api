package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.repository.CozinhaRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class ConsultaCozinhaService {

    private static final String COZINHA = "Cozinha";
    private final CozinhaRepository repository;

    @Transactional(readOnly = true)
    public List<Cozinha> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Cozinha buscar(Long cozinhaId) {
        return repository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), COZINHA, cozinhaId)
                ));
    }

    @Transactional(readOnly = true)
    public Cozinha buscarCozinhaRestaurante(Restaurante restaurante) {
        if (isNull(restaurante.getCozinha()) || isNull(restaurante.getCozinha().getId())) {
            throw new CampoObrigatorioException(
                    String.format(MensagemErro.CAMPO_OBRIGATORIO.getDescricao(), COZINHA)
            );
        }

        Long cozinhaId = restaurante.getCozinha().getId();
        return repository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), COZINHA, cozinhaId)
                ));
    }
}
