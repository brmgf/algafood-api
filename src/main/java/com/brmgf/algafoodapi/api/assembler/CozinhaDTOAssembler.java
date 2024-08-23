package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.assembler.utils.DTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.CozinhaDTO;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CozinhaDTOAssembler implements DTOAssembler<CozinhaDTO, Cozinha> {

    private final ModelMapper modelMapper;

    @Override
    public CozinhaDTO toDTO(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    @Override
    public List<CozinhaDTO> toCollectionDTO(Collection<Cozinha> cozinhas) {
        return cozinhas.stream().map(this::toDTO).toList();
    }
}
