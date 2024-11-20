package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.CidadeDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.CidadeInputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.CidadeInput;
import com.brmgf.algafoodapi.api.domain.dto.CidadeDTO;
import com.brmgf.algafoodapi.service.cadastro.CadastroCidadeService;
import com.brmgf.algafoodapi.service.consulta.ConsultaCidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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

@Tag(name = "Cidades", description = "Operações relacionadas às cidades")
@RequiredArgsConstructor
@RequestMapping("/cidades")
@RestController
public class CidadeController {

    private final ConsultaCidadeService consultaService;
    private final CadastroCidadeService cadastroService;
    private final CidadeDTOAssembler assembler;
    private final CidadeInputDisassembler disassembler;

    @Operation(
            summary = "Lista todas as cidades",
            description = "Este endpoint retorna uma lista de todas as cidades cadastradas no sistema."
    )
    @GetMapping
    public List<CidadeDTO> listar() {
        return assembler.toCollectionDTO(consultaService.listar());
    }

    @Operation(summary = "Busca uma cidade", description = "Este endpoint retorna uma cidade cadastrada no sistema.")
    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@Parameter(description = "Identificador único da cidade", example = "521") @PathVariable Long cidadeId) {
        return assembler.toDTO(consultaService.buscar(cidadeId));
    }

    @Operation(summary = "Salva uma cidade", description = "Este endpoint cadastra e retorna uma nova cidade no sistema.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CidadeDTO salvar(@RequestBody @Valid CidadeInput input) {
        return assembler.toDTO(cadastroService.salvar(disassembler.toObjectModel(input)));
    }

    @Operation(summary = "Atualiza uma cidade", description = "Este endpoint atualiza e retorna uma cidade.")
    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@Parameter(description = "Identificador único da cidade", example = "521") @PathVariable Long cidadeId,
                               @RequestBody @Valid CidadeInput input) {
        return assembler.toDTO(cadastroService.atualizar(cidadeId, disassembler.toObjectModel(input)));
    }

    @Operation(summary = "Remove uma cidade", description = "Este endpoint remove uma cidade cadastrada no sistema.")
    @DeleteMapping("/{cidadeId}")
    public void remover(@Parameter(description = "Identificador único da cidade", example = "521") @PathVariable Long cidadeId) {
        cadastroService.remover(cidadeId);
    }
}
