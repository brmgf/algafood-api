package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.repository.RestauranteRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaCidadeService;
import com.brmgf.algafoodapi.service.consulta.ConsultaCozinhaService;
import com.brmgf.algafoodapi.service.consulta.ConsultaRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CadastroRestauranteService {

    private final RestauranteRepository repository;
    private final ConsultaRestauranteService consultaService;
    private final ConsultaCozinhaService consultaCozinhaService;
    private final ConsultaCidadeService consultaCidadeService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        try {
            restaurante.setCozinha(consultaCozinhaService.buscarCozinhaRestaurante(restaurante));
            restaurante.getEndereco().setCidade(consultaCidadeService.buscarCidadeEndereco(restaurante.getEndereco()));

            return repository.save(restaurante);
        } catch (EntidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Transactional
    public Restaurante atualizar(Long restauranteId, Restaurante novoRestaurante) {
        Restaurante restaurante = consultaService.buscar(restauranteId);

        BeanUtils.copyProperties(novoRestaurante, restaurante,
                "id", "formasPagamento", "dataHoraCadastro", "produtos");

        try {
            restaurante.setCozinha(consultaCozinhaService.buscarCozinhaRestaurante(novoRestaurante));
            restaurante.getEndereco().setCidade(consultaCidadeService.buscarCidadeEndereco(novoRestaurante.getEndereco()));

            return repository.save(restaurante);
        } catch (EntidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Transactional
    public void remover(Long restauranteId) {
        Restaurante restaurante = consultaService.buscar(restauranteId);

        repository.delete(restaurante);
        repository.flush();
    }

    @Transactional
    public Restaurante atualizarDadosParcialmente(Long restauranteId, Map<String, Object> dadosOrigem) {
        Restaurante restaurante = consultaService.buscar(restauranteId);

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field, restaurante, novoValor);
        });

        return repository.save(restaurante);
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restaurante = consultaService.buscar(restauranteId);
        restaurante.setAtivo(true);
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restaurante = consultaService.buscar(restauranteId);
        restaurante.setAtivo(false);
    }
}
