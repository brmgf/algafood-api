package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.utils.ProdutoDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.ProdutoInputDisassembler;
import com.brmgf.algafoodapi.api.domain.dto.ProdutoDTO;
import com.brmgf.algafoodapi.api.domain.input.ProdutoInput;
import com.brmgf.algafoodapi.domain.model.Produto;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.service.cadastro.CadastroProdutoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaProdutoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaRestauranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/restaurantes/{restauranteId}/produtos")
@RestController
public class ProdutoController {

    private final ConsultaRestauranteService consultaRestauranteService;
    private final ConsultaProdutoService consultaService;
    private final CadastroProdutoService cadastroService;
    private final ProdutoDTOAssembler assembler;
    private final ProdutoInputDisassembler disassembler;

    @GetMapping
    public List<ProdutoDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
        return assembler.toCollectionDTO(restaurante.getProdutos());
    }

    @GetMapping("/{produtoId}")
    public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
        return assembler.toDTO(consultaService.buscarProdutoRestaurante(restaurante, produtoId));
    }

    @PostMapping
    public ProdutoDTO adicionar(@PathVariable Long restauranteId,
                                @RequestBody @Valid ProdutoInput input) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
        Produto produto = disassembler.toObjectModel(input);
        produto.setRestaurante(restaurante);

        return assembler.toDTO(cadastroService.adicionar(produto));
    }

    @PutMapping("/{produtoId}")
    public ProdutoDTO atualizar(@PathVariable Long restauranteId,
                                @PathVariable Long produtoId,
                                @RequestBody @Valid ProdutoInput input) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
        return assembler.toDTO(cadastroService
                .atualizar(produtoId, disassembler.toObjectModel(input), restaurante));
    }
}
