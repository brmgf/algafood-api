package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.domain.dto.CidadeDTO;
import com.brmgf.algafoodapi.api.domain.input.CidadeInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Cidades", description = "Operações relacionadas às cidades")
public interface CidadeOpenApi {

    @Operation(summary = "Lista todas as cidades", description = "Este endpoint retorna uma lista de todas as cidades cadastradas no sistema.")
    public List<CidadeDTO> listar();

    @Operation(summary = "Busca uma cidade", description = "Este endpoint retorna uma cidade cadastrada no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi possível realizar operação. Cidade não encontrado(a).")
    })
    public CidadeDTO buscar(@Parameter(description = "Identificador único da cidade", example = "521") Long cidadeId);

    @Operation(summary = "Salva uma cidade", description = "Este endpoint cadastra e retorna uma nova cidade no sistema.")
    public CidadeDTO salvar(CidadeInput input);

    @Operation(summary = "Atualiza uma cidade", description = "Este endpoint atualiza e retorna uma cidade.")
    public CidadeDTO atualizar(@Parameter(description = "Identificador único da cidade", example = "521") Long cidadeId, CidadeInput input);
    @Operation(summary = "Remove uma cidade", description = "Este endpoint remove uma cidade cadastrada no sistema.")
    public void remover(@Parameter(description = "Identificador único da cidade", example = "521") Long cidadeId);
}
