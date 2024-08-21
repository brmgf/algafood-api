package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.repository.GrupoRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaGrupoService;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CadastroGrupoService {

    private static final String NOME_ENTIDADE = "Grupo";

    private final GrupoRepository grupoRepository;
    private final ConsultaGrupoService consultaGrupoService;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public Grupo atualizar(Long grupoId, Grupo novoGrupo) {
        Grupo grupo = consultaGrupoService.buscar(grupoId);

        BeanUtils.copyProperties(novoGrupo, grupo, "id");
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void remover(Long grupoId) {
        try {
            Grupo grupo = consultaGrupoService.buscar(grupoId);

            grupoRepository.delete(grupo);
            grupoRepository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntidadeEmUsoException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_EM_USO.getDescricao(), NOME_ENTIDADE, grupoId)
            );
        }
    }
}
