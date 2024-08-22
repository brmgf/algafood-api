package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeCadastradaException;
import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
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

    private static final String ESTADO = "Estado";

    private final EstadoRepository repository;

    @Transactional(readOnly = true)
    public List<Estado> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Estado buscar(Long estadoId) {
        return repository.findById(estadoId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), ESTADO, estadoId)
                ));
    }

    @Transactional(readOnly = true)
    public void validarEstadoCadastradoComMesmoNome(Estado novoEstado, @Nullable Long estadoId) {
        Optional<Estado> estado = repository.findByNomeIgnoreCase(novoEstado.getNome());
        if (estado.isPresent()) {
            if (isNull(estadoId)) {
                throw new EntidadeCadastradaException(
                        String.format(MensagemErro.ENTIDADE_JA_CADASTRADA.getDescricao(), ESTADO)
                );
            }

            if (nonNull(estadoId)) {
                Optional<Estado> estadoExistente = repository.findById(estadoId);
                if (estadoExistente.isPresent() && !estadoExistente.get().getNome().equalsIgnoreCase(novoEstado.getNome())) {
                    throw new EntidadeCadastradaException(
                            String.format(MensagemErro.ENTIDADE_JA_CADASTRADA.getDescricao(), ESTADO)
                    );
                }
            }
        }
    }
}
