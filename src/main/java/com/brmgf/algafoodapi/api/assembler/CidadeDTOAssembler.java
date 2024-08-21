package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.domain.dto.CidadeDTO;
import com.brmgf.algafoodapi.domain.model.Cidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CidadeDTOAssembler implements DTOAssembler<CidadeDTO, Cidade> {

    private final ModelMapper modelMapper;

    public CidadeDTO toDTO(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    public List<CidadeDTO> toCollectionDTO(List<Cidade> cidades) {
        return cidades.stream().map(this::toDTO).toList();
    }
}
