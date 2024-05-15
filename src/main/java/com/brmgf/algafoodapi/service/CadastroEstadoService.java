package com.brmgf.algafoodapi.service;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeCadastradaException;
import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.domain.model.Estado;
import com.brmgf.algafoodapi.domain.repository.EstadoRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class CadastroEstadoService {

    private static final String NOME_ENTIDADE = "Estado";

    private final EstadoRepository estadoRepository;

    @Transactional(readOnly = true)
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Estado buscar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElse(null);
    }

    @Transactional
    public Estado salvar(Estado estado) {
        validarEstadoCadastrado(estado);
        return estadoRepository.save(estado);
    }

    @Transactional
    public Estado atualizar(Long estadoId, Estado novoEstado) {
        Estado estado = this.buscar(estadoId);

        if (isNull(estado)) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, estadoId)
            );
        }

        validarEstadoCadastrado(novoEstado);

        BeanUtils.copyProperties(novoEstado, estado, "id");
        return estadoRepository.save(estado);
    }

    @Transactional
    public void remover(Long estadoId) {
        try {
            Estado estado = this.buscar(estadoId);
            if (isNull(estado)) {
                throw new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, estadoId)
                );
            }

            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntidadeEmUsoException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_EM_USO.getDescricao(), NOME_ENTIDADE, estadoId)
            );
        }
    }

    @Transactional(readOnly = true)
    public void validarEstadoCadastrado(Estado estado) {
        boolean existeEstadoComMesmoNome = estadoRepository.existsByNome(estado.getNome());
        if (existeEstadoComMesmoNome) {
            throw new EntidadeCadastradaException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_JA_CADASTRADA.getDescricao(), NOME_ENTIDADE)
            );
        }
    }

    @Transactional(readOnly = true)
    public Estado buscarEstadoCidade(Cidade cidade) {
        if (isNull(cidade.getEstado()) || isNull(cidade.getEstado().getId())) {
            throw new CampoObrigatorioException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_CAMPO_OBRIGATORIO.getDescricao(), NOME_ENTIDADE)
            );
        }

        Long estadoId = cidade.getEstado().getId();
        Estado estado = this.buscar(estadoId);
        if (isNull(estado)) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, estadoId)
            );
        }

        return estado;
    }
}
