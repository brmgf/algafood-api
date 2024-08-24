package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.GrupoDTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.GrupoDTO;
import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.model.Usuario;
import com.brmgf.algafoodapi.service.cadastro.CadastroUsuarioGrupoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaGrupoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaUsuarioService;
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
@RequestMapping("/usuarios/{usuarioId}/grupos")
@RestController
public class UsuarioGrupoController {

    private final CadastroUsuarioGrupoService service;
    private final ConsultaUsuarioService consultaUsuarioService;
    private final ConsultaGrupoService consultaGrupoService;
    private final GrupoDTOAssembler assembler;

    @GetMapping
    public List<GrupoDTO> listar(@PathVariable Long usuarioId) {
        Usuario usuario = consultaUsuarioService.buscar(usuarioId);
        return assembler.toCollectionDTO(usuario.getGrupos());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{grupoId}")
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        Usuario usuario = consultaUsuarioService.buscar(usuarioId);
        Grupo grupo = consultaGrupoService.buscar(grupoId);
        service.associarGrupoUsuario(usuario, grupo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{grupoId}")
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        Usuario usuario = consultaUsuarioService.buscar(usuarioId);
        Grupo grupo = consultaGrupoService.buscarGrupoUsuario(usuario, grupoId);
        service.desassociarGrupoUsuario(usuario, grupo);
    }
}
