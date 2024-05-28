package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.repository.RestauranteRepository;
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

    private final RestauranteRepository restauranteRepository;
    private final ConsultaRestauranteService consultaRestauranteService;
    private final CadastroCozinhaService cadastroCozinhaService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        restaurante.setCozinha(cadastroCozinhaService.buscarCozinhaRestaurante(restaurante));
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long restauranteId, Restaurante novoRestaurante) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);

        BeanUtils.copyProperties(novoRestaurante, restaurante, "id", "formasPagamento");
        restaurante.setCozinha(cadastroCozinhaService.buscarCozinhaRestaurante(novoRestaurante));
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void remover(Long restauranteId) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);

        restauranteRepository.delete(restaurante);
        restauranteRepository.flush();
    }

    @Transactional
    public Restaurante atualizarDadosParcialmente(Long restauranteId, Map<String, Object> dadosOrigem) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field, restaurante, novoValor);
        });

        return restauranteRepository.save(restaurante);
    }
}
