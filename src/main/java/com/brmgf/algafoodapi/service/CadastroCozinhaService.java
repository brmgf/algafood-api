package com.brmgf.algafoodapi.service;

import com.brmgf.algafoodapi.domain.model.Cozinha;
import com.brmgf.algafoodapi.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CadastroCozinhaService {

    private final CozinhaRepository cozinhaRepository;

    @Transactional(readOnly = true)
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cozinha buscar(Long id) {
        return cozinhaRepository.findById(id).orElse(null);
    }
}
