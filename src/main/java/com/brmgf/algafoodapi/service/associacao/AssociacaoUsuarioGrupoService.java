package com.brmgf.algafoodapi.service.associacao;

import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociacaoUsuarioGrupoService {

    @Transactional
    public void associarGrupoUsuario(Usuario usuario, Grupo grupo) {
        usuario.getGrupos().add(grupo);
    }

    @Transactional
    public void desassociarGrupoUsuario(Usuario usuario, Grupo grupo) {
        usuario.getGrupos().remove(grupo);
    }
}
