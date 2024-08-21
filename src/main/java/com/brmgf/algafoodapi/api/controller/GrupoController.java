package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.GrupoDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.GrupoInputDisassembler;
import com.brmgf.algafoodapi.api.domain.dto.GrupoDTO;
import com.brmgf.algafoodapi.api.domain.input.GrupoInput;
import com.brmgf.algafoodapi.service.cadastro.CadastroGrupoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaGrupoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/grupos")
@RestController
public class GrupoController {

    private final ConsultaGrupoService consultaGrupoService;
    private final CadastroGrupoService cadastroGrupoService;
    private final GrupoDTOAssembler grupoDTOAssembler;
    private final GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoDTO> listar() {
        return grupoDTOAssembler.toCollectionDTO(consultaGrupoService.listar());
    }

    @GetMapping("/{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        return grupoDTOAssembler.toDTO(consultaGrupoService.buscar(grupoId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public GrupoDTO salvar(@RequestBody @Valid GrupoInput grupoInput) {
        return grupoDTOAssembler
                .toDTO(cadastroGrupoService.salvar(grupoInputDisassembler.toObjectModel(grupoInput)));
    }

    @PutMapping("/{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        return grupoDTOAssembler
                .toDTO(cadastroGrupoService.atualizar(grupoId, grupoInputDisassembler.toObjectModel(grupoInput)));
    }

    @DeleteMapping("/{grupoId}")
    public void remover(@PathVariable Long grupoId) {
        cadastroGrupoService.remover(grupoId);
    }
}
