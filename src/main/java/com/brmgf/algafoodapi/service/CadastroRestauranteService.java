package com.brmgf.algafoodapi.service;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.repository.RestauranteRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class CadastroRestauranteService {

    private static final String NOME_ENTIDADE = "Restaurante";

    private final RestauranteRepository restauranteRepository;
    private final CadastroCozinhaService cadastroCozinhaService;

    @Transactional(readOnly = true)
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).orElse(null);
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        restaurante.setCozinha(cadastroCozinhaService.buscarCozinhaRestaurante(restaurante));
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long restauranteId, Restaurante novoRestaurante) {
        Restaurante restaurante = this.buscar(restauranteId);

        if (isNull(restaurante)) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, restauranteId)
            );
        }

        BeanUtils.copyProperties(novoRestaurante, restaurante, "id");
        restaurante.setCozinha(cadastroCozinhaService.buscarCozinhaRestaurante(novoRestaurante));
        return salvar(restaurante);
    }

    @Transactional
    public void remover(Long restauranteId) {
        Restaurante restaurante = this.buscar(restauranteId);
        if (isNull(restaurante)) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_NAO_ENCONTRADA.getDescricao(), NOME_ENTIDADE, restauranteId)
            );
        }
        restauranteRepository.delete(restaurante);
        restauranteRepository.flush();
    }
}
