package com.brmgf.algafoodapi.api.assembler.utils;

import com.brmgf.algafoodapi.api.domain.dto.ProdutoDTO;
import com.brmgf.algafoodapi.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProdutoDTOAssembler implements DTOAssembler<ProdutoDTO, Produto> {

    private final ModelMapper modelMapper;

    @Override
    public ProdutoDTO toDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    @Override
    public List<ProdutoDTO> toCollectionDTO(Collection<Produto> produtos) {
        return produtos.stream().map(this::toDTO).toList();
    }
}
