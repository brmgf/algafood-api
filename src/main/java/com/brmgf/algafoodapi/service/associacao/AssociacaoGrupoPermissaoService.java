package com.brmgf.algafoodapi.service.associacao;

import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.model.Permissao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociacaoGrupoPermissaoService {

    @Transactional
    public void associar(Grupo grupo, Permissao permissao) {
        grupo.getPermissoes().add(permissao);
    }

    @Transactional
    public void desassociar(Grupo grupo, Permissao permissao) {
        grupo.getPermissoes().remove(permissao);
    }
}
