package com.brmgf.algafoodapi.service;

import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import com.brmgf.algafoodapi.domain.repository.CozinhaRepository;
import jakarta.annotation.Resource;
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
    public Cozinha atualizar(Long id, Cozinha cozinha) {
        Cozinha cozinhaAtual = this.buscar(id);
        if (nonNull(cozinhaAtual)) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            return cozinhaRepository.save(cozinhaAtual);
        }
        return null;
    }

    @Transactional
    public void remover(Long cozinhaId) {
        try {
            Cozinha cozinha = this.buscar(cozinhaId);
            if (isNull(cozinha)) {
                throw new EntidadeNaoEncontradaException(
                        String.format("Não foi possível excluir registro. Cozinha de código %d não encontrada", cozinhaId)
                );
            }
            cozinhaRepository.delete(cozinha);
            cozinhaRepository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(
                String.format("Não foi possível excluir registro. Cozinha de código %d está em uso", cozinhaId));
        }
    }
}
