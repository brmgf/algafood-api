package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.assembler.utils.DTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.EstadoDTO;
import com.brmgf.algafoodapi.domain.model.Estado;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class EstadoDTOAssember implements DTOAssembler<EstadoDTO, Estado> {

    private final ModelMapper modelMapper;

    @Override
    public EstadoDTO toDTO(Estado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }

    @Override
    public List<EstadoDTO> toCollectionDTO(Collection<Estado> estados) {
        return estados.stream().map(this::toDTO).toList();
    }
}
