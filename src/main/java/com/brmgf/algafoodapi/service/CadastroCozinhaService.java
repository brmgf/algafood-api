package com.brmgf.algafoodapi.service;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.repository.CozinhaRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class CadastroCozinhaService {

    private static final String NOME_ENTIDADE = "Cozinha";

    private final CozinhaRepository cozinhaRepository;

    @Transactional(readOnly = true)
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cozinha buscar(Long id) {
        return cozinhaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Long cozinhaId, Cozinha novaCozinha) {
        Cozinha cozinha = this.buscar(cozinhaId);
        if (nonNull(cozinha)) {
            BeanUtils.copyProperties(novaCozinha, cozinha, "id");
            return cozinhaRepository.save(cozinha);
        }
        return null;
    }

    @Transactional
    public void remover(Long cozinhaId) {
        try {
            Cozinha cozinha = this.buscar(cozinhaId);
            if (isNull(cozinha)) {
                throw new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, cozinhaId)
                );
            }
            cozinhaRepository.delete(cozinha);
            cozinhaRepository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntidadeEmUsoException(
                String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_EM_USO.getDescricao(), NOME_ENTIDADE, cozinhaId));
        }
    }

    @Transactional
    public Cozinha buscarCozinhaRestaurante(Restaurante restaurante) {
        if (isNull(restaurante.getCozinha()) || isNull(restaurante.getCozinha().getId())) {
            throw new CampoObrigatorioException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_CAMPO_OBRIGATORIO.getDescricao(), NOME_ENTIDADE)
            );
        }

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = this.buscar(cozinhaId);
        if (isNull(cozinha)) {
            throw new CampoObrigatorioException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, cozinhaId)
            );
        }

        return cozinha;
    }
}
