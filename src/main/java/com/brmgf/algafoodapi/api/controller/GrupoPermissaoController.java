package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.PermissaoDTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.PermissaoDTO;
import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.model.Permissao;
import com.brmgf.algafoodapi.service.cadastro.CadastroGrupoPermissaoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaGrupoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaPermissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/grupos/{grupoId}/permissoes")
@RestController
public class GrupoPermissaoController {

    private final CadastroGrupoPermissaoService cadastroService;
    private final ConsultaGrupoService consultaGrupoService;
    private final ConsultaPermissaoService consultaPermissaoService;
    private final PermissaoDTOAssembler assembler;

    @GetMapping
    public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
        Grupo grupo = consultaGrupoService.buscar(grupoId);
        return assembler.toCollectionDTO(grupo.getPermissoes());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{permissaoId}")
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        Grupo grupo = consultaGrupoService.buscar(grupoId);
        Permissao permissao = consultaPermissaoService.buscar(permissaoId);
        cadastroService.associar(grupo, permissao);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{permissaoId}")
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        Grupo grupo = consultaGrupoService.buscar(grupoId);
        Permissao permissao = consultaPermissaoService.buscarPermissaoGrupo(grupo, permissaoId);
        cadastroService.desassociar(grupo, permissao);
    }
}
