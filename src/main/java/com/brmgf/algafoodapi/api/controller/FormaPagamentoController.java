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

    private final ConsultaFormaPagamentoService consultaFormaPagamentoService;
    private final CadastroFormaPagamentoService cadastroFormaPagamentoService;
    private final FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
    private final FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping
    public List<FormaPagamentoDTO> listar() {
        return formaPagamentoDTOAssembler.toCollectionDTO(consultaFormaPagamentoService.listar());
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO buscar(@PathVariable Long formaPagamentoId) {
        return formaPagamentoDTOAssembler.toDTO(consultaFormaPagamentoService.buscar(formaPagamentoId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FormaPagamentoDTO salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        return formaPagamentoDTOAssembler
                .toDTO(cadastroFormaPagamentoService.salvar(formaPagamentoInputDisassembler.toObjectModel(formaPagamentoInput)));
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        return formaPagamentoDTOAssembler
                .toDTO(cadastroFormaPagamentoService.atualizar(formaPagamentoId, formaPagamentoInputDisassembler.toObjectModel(formaPagamentoInput)));
    }

    @DeleteMapping("/{formaPagamentoId}")
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamentoService.remover(formaPagamentoId);
    }
}
