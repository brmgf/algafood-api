package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.domain.model.CozinhaDTO;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CozinhaDTOAssembler {

    private final ModelMapper modelMapper;

    public CozinhaDTO toDTO(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    public List<CozinhaDTO> toCollectionDTO(List<Cozinha> cozinhas) {
        return cozinhas.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
