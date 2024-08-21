package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.domain.dto.RestauranteDTO;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RestauranteDTOAssembler implements DTOAssembler<RestauranteDTO, Restaurante> {

    private final ModelMapper modelMapper;

    public RestauranteDTO toDTO(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(this::toDTO).toList();
    }
}
