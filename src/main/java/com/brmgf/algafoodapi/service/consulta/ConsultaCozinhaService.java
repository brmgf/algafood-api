package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.CozinhaNaoEncontradaException;
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

    private static final String NOME_ENTIDADE = "Cozinha";

    private final CozinhaRepository cozinhaRepository;

    @Transactional(readOnly = true)
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cozinha buscar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }

    @Transactional(readOnly = true)
    public Cozinha buscarCozinhaRestaurante(Restaurante restaurante) {
        if (isNull(restaurante.getCozinha()) || isNull(restaurante.getCozinha().getId())) {
            throw new CampoObrigatorioException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_CAMPO_OBRIGATORIO.getDescricao(), NOME_ENTIDADE)
            );
        }

        Long cozinhaId = restaurante.getCozinha().getId();
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }
}
