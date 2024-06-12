package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.domain.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaCidadeService {

    private final String NOME_ENTIDADE = "Cidade";

    private final CidadeRepository cidadeRepository;

    @Transactional(readOnly = true)
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cidade buscar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }
}
