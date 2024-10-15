package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.FotoProdutoDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.RestauranteFotoProdutoInputDisassembler;
import com.brmgf.algafoodapi.api.domain.dto.FotoProdutoDTO;
import com.brmgf.algafoodapi.api.domain.input.RestauranteFotoProdutoInput;
import com.brmgf.algafoodapi.service.cadastro.CatalogoFotoProdutoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
@RestController
public class RestauranteFotoProdutoController {

    private final CatalogoFotoProdutoService service;
    private final ConsultaProdutoService consultaProdutoService;
    private final RestauranteFotoProdutoInputDisassembler disassembler;
    private final FotoProdutoDTOAssembler assembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                        @Valid RestauranteFotoProdutoInput input) throws IOException {
        var produto = consultaProdutoService.buscarProdutoRestaurante(restauranteId, produtoId);
        var fotoProduto = disassembler.toObjectModel(produto, input);
        return assembler.toDTO(service.salvar(fotoProduto));
    }
}
