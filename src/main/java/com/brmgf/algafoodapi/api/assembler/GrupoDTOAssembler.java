package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.assembler.utils.DTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.GrupoDTO;
import com.brmgf.algafoodapi.domain.model.Grupo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class GrupoDTOAssembler implements DTOAssembler<GrupoDTO, Grupo> {

    private final ModelMapper modelMapper;

    @Override
    public GrupoDTO toDTO(Grupo grupo) {
        return modelMapper.map(grupo, GrupoDTO.class);
    }

    @Override
    public List<GrupoDTO> toCollectionDTO(Collection<Grupo> grupos) {
        return grupos.stream().map(this::toDTO).toList();
    }
}
