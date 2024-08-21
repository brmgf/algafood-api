package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.GrupoNaoEncontradoException;
import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaGrupoService {

    private final GrupoRepository grupoRepository;

    @Transactional(readOnly = true)
    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Grupo buscar(Long grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }
}
