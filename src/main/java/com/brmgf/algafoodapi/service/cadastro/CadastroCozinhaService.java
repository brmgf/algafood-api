package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import com.brmgf.algafoodapi.domain.repository.CozinhaRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaCozinhaService;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CadastroCozinhaService {

    private final CozinhaRepository repository;
    private final ConsultaCozinhaService consultaService;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Long cozinhaId, Cozinha novaCozinha) {
        Cozinha cozinha = consultaService.buscar(cozinhaId);

        BeanUtils.copyProperties(novaCozinha, cozinha, "id");
        return repository.save(cozinha);
    }

    @Transactional
    public void remover(Long cozinhaId) {
        try {
            Cozinha cozinha = consultaService.buscar(cozinhaId);

            repository.delete(cozinha);
            repository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntidadeEmUsoException(
                String.format(MensagemErro.ENTIDADE_EM_USO.getDescricao(), "Cozinha", cozinhaId)
            );
        }
    }
}
