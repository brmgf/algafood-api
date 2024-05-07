package com.brmgf.algafoodapi.service;

import com.brmgf.algafoodapi.domain.model.Estado;
import com.brmgf.algafoodapi.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaEstadoService {

    private final EstadoRepository estadoRepository;

    @Transactional(readOnly = true)
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }
}
