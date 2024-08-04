package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.input.RestauranteInput;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RestauranteInputDisassembler {

    private final ModelMapper modelMapper;

    public Restaurante toObjectModel(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }
}
