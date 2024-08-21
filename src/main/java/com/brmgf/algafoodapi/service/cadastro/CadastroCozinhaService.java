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

    private static final String NOME_ENTIDADE = "Cozinha";

    private final CozinhaRepository cozinhaRepository;
    private final ConsultaCozinhaService consultaCozinhaService;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Long cozinhaId, Cozinha novaCozinha) {
        Cozinha cozinha = consultaCozinhaService.buscar(cozinhaId);

        BeanUtils.copyProperties(novaCozinha, cozinha, "id");
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void remover(Long cozinhaId) {
        try {
            Cozinha cozinha = consultaCozinhaService.buscar(cozinhaId);

            cozinhaRepository.delete(cozinha);
            cozinhaRepository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntidadeEmUsoException(
                String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_EM_USO.getDescricao(), NOME_ENTIDADE, cozinhaId)
            );
        }
    }
}
