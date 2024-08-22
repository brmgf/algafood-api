package com.brmgf.algafoodapi.api.disassembler;


import com.brmgf.algafoodapi.api.disassembler.utils.InputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.RestauranteInput;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RestauranteInputDisassembler implements InputDisassembler<Restaurante, RestauranteInput> {

    private final ModelMapper modelMapper;

    @Override
    public Restaurante toObjectModel(RestauranteInput input) {
        return modelMapper.map(input, Restaurante.class);
    }
}
