package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.UsuarioDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.UsuarioInputDisassembler;
import com.brmgf.algafoodapi.api.disassembler.UsuarioSenhaInputDisassembler;
import com.brmgf.algafoodapi.api.domain.dto.UsuarioDTO;
import com.brmgf.algafoodapi.api.domain.input.SenhaInput;
import com.brmgf.algafoodapi.api.domain.input.UsuarioInput;
import com.brmgf.algafoodapi.api.domain.input.UsuarioSenhaInput;
import com.brmgf.algafoodapi.service.cadastro.CadastroUsuarioService;
import com.brmgf.algafoodapi.service.consulta.ConsultaUsuarioService;
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
@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    private final ConsultaUsuarioService consultaService;
    private final CadastroUsuarioService cadastroService;
    private final UsuarioDTOAssembler assembler;
    private final UsuarioInputDisassembler usuarioDisassembler;
    private final UsuarioSenhaInputDisassembler usuarioSenhaDisassembler;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return assembler.toCollectionDTO(consultaService.listar());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscar(@PathVariable Long usuarioId) {
        return assembler.toDTO(consultaService.buscar(usuarioId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UsuarioDTO salvar(@RequestBody @Valid UsuarioInput input) {
        return assembler.toDTO(cadastroService.salvar(usuarioDisassembler.toObjectModel(input)));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@PathVariable Long usuarioId,
                                @RequestBody @Valid UsuarioSenhaInput input) {
        return assembler.toDTO(cadastroService.atualizar(usuarioId, usuarioSenhaDisassembler.toObjectModel(input)));
    }

    @PutMapping("/{usuarioId}/senha")
    public void alterarSenhar(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput input) {
        cadastroService.alterarSenha(usuarioId, input);
    }

    @DeleteMapping("/{usuarioId}")
    public void remover(@PathVariable Long usuarioId) {
        cadastroService.remover(usuarioId);
    }
}
