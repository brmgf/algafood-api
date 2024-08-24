package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CadastroUsuarioGrupoService {

    @Transactional
    public void associarGrupoUsuario(Usuario usuario, Grupo grupo) {
        usuario.getGrupos().add(grupo);
    }

    @Transactional
    public void desassociarGrupoUsuario(Usuario usuario, Grupo grupo) {
        usuario.getGrupos().remove(grupo);
    }
}
