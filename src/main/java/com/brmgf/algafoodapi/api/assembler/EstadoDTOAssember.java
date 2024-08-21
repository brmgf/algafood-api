package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.domain.dto.EstadoDTO;
import com.brmgf.algafoodapi.domain.model.Estado;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class EstadoDTOAssember implements DTOAssembler<EstadoDTO, Estado> {

    private final ModelMapper modelMapper;

    public EstadoDTO toDTO(Estado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }

    public List<EstadoDTO> toCollectionDTO(List<Estado> estados) {
        return estados.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
