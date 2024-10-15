package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.assembler.utils.DTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.FotoProdutoDTO;
import com.brmgf.algafoodapi.domain.model.FotoProduto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FotoProdutoDTOAssembler implements DTOAssembler<FotoProdutoDTO, FotoProduto> {

    private final ModelMapper modelMapper;

    @Override
    public FotoProdutoDTO toDTO(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoProdutoDTO.class);
    }

    @Override
    public List<FotoProdutoDTO> toCollectionDTO(Collection<FotoProduto> fotosProdutos) {
        return fotosProdutos.stream().map(this::toDTO).toList();
    }
}
