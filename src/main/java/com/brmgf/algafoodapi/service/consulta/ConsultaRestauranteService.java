package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.repository.RestauranteRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaRestauranteService {

    private static final String RESTAURANTE = "Restaurante";

    private final RestauranteRepository repository;

    @Transactional(readOnly = true)
    public List<Restaurante> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Restaurante buscar(Long restauranteId) {
        return repository.findById(restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), RESTAURANTE, restauranteId)
                ));
    }
}
