package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.model.Permissao;
import com.brmgf.algafoodapi.service.consulta.ConsultaGrupoPermissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CadastroGrupoPermissaoService {

    private final ConsultaGrupoPermissaoService consultaService;

    @Transactional
    public void associar(Grupo grupo, Long permissaoId) {
        Permissao permissao = consultaService.buscar(permissaoId);
        grupo.getPermissoes().add(permissao);
    }

    @Transactional
    public void desassociar(Grupo grupo, Long permissaoId) {
        Permissao permissao = consultaService.buscar(permissaoId);
        grupo.getPermissoes().remove(permissao);
    }
}
