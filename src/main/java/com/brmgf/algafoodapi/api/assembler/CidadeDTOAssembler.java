package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.assembler.utils.DTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.CidadeDTO;
import com.brmgf.algafoodapi.domain.model.Cidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CidadeDTOAssembler implements DTOAssembler<CidadeDTO, Cidade> {

    private final ModelMapper modelMapper;

    @Override
    public CidadeDTO toDTO(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    @Override
    public List<CidadeDTO> toCollectionDTO(Collection<Cidade> cidades) {
        return cidades.stream().map(this::toDTO).toList();
    }
}
