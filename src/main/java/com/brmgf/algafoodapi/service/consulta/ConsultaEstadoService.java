package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeCadastradaException;
import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.EstadoNaoEncontradoException;
import com.brmgf.algafoodapi.domain.model.Estado;
import com.brmgf.algafoodapi.domain.repository.EstadoRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class ConsultaEstadoService {

    private static final String NOME_ENTIDADE = "Estado";

    private final EstadoRepository estadoRepository;

    @Transactional(readOnly = true)
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Estado buscar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(
                () -> new EstadoNaoEncontradoException(estadoId));
    }

    @Transactional(readOnly = true)
    public void validarEstadoCadastradoComMesmoNome(Estado novoEstado, @Nullable Long estadoId) {
        Optional<Estado> estado = estadoRepository.findByNomeIgnoreCase(novoEstado.getNome());
        if (estado.isPresent()) {
            if (isNull(estadoId)) {
                throw new EntidadeCadastradaException(
                        String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_JA_CADASTRADA.getDescricao(), NOME_ENTIDADE)
                );
            }

            if (nonNull(estadoId)) {
                Optional<Estado> estadoExistente = estadoRepository.findById(estadoId);
                if (estadoExistente.isPresent() && !estadoExistente.get().getNome().equalsIgnoreCase(novoEstado.getNome())) {
                    throw new EntidadeCadastradaException(
                            String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_JA_CADASTRADA.getDescricao(), NOME_ENTIDADE)
                    );
                }
            }
        }
    }
}
