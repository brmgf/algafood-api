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

    private final GrupoRepository repository;
    private final ConsultaGrupoService consultaService;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return repository.save(grupo);
    }

    @Transactional
    public Grupo atualizar(Long grupoId, Grupo novoGrupo) {
        Grupo grupo = consultaService.buscar(grupoId);

        BeanUtils.copyProperties(novoGrupo, grupo, "id");
        return repository.save(grupo);
    }

    @Transactional
    public void remover(Long grupoId) {
        try {
            Grupo grupo = consultaService.buscar(grupoId);

            repository.delete(grupo);
            repository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntidadeEmUsoException(
                    String.format(MensagemErro.ENTIDADE_EM_USO.getDescricao(), "Grupo", grupoId)
            );
        }
    }
}
