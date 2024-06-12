package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaRestauranteService {

    private final RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }
}
