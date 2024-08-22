package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.FormaPagamentoDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.FormaPagamentoInputDisassembler;
import com.brmgf.algafoodapi.api.domain.dto.FormaPagamentoDTO;
import com.brmgf.algafoodapi.api.domain.input.FormaPagamentoInput;
import com.brmgf.algafoodapi.service.cadastro.CadastroFormaPagamentoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaFormaPagamentoService;
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
@RequestMapping("/formas-pagamento")
@RestController
public class FormaPagamentoController {

    private final ConsultaFormaPagamentoService consultaService;
    private final CadastroFormaPagamentoService cadastroService;
    private final FormaPagamentoDTOAssembler assembler;
    private final FormaPagamentoInputDisassembler disassembler;

    @GetMapping
    public List<FormaPagamentoDTO> listar() {
        return assembler.toCollectionDTO(consultaService.listar());
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO buscar(@PathVariable Long formaPagamentoId) {
        return assembler.toDTO(consultaService.buscar(formaPagamentoId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FormaPagamentoDTO salvar(@RequestBody @Valid FormaPagamentoInput input) {
        return assembler.toDTO(cadastroService.salvar(disassembler.toObjectModel(input)));
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId,
                                       @RequestBody @Valid FormaPagamentoInput input) {
        return assembler.toDTO(cadastroService.atualizar(formaPagamentoId, disassembler.toObjectModel(input)));
    }

    @DeleteMapping("/{formaPagamentoId}")
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroService.remover(formaPagamentoId);
    }
}
