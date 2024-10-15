package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.domain.input.RestauranteFotoProdutoInput;
import com.brmgf.algafoodapi.domain.model.FotoProduto;
import com.brmgf.algafoodapi.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RestauranteFotoProdutoInputDisassembler {

    public FotoProduto toObjectModel(Produto produto, RestauranteFotoProdutoInput input) {
        var arquivo = input.getArquivo();

        return FotoProduto.builder()
                .produto(produto)
                .nomeArquivo(arquivo.getOriginalFilename())
                .contentType(arquivo.getContentType())
                .tamanho(arquivo.getSize()).build();
    }
}
