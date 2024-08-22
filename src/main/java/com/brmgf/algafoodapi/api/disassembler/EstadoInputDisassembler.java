package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.disassembler.utils.InputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.EstadoInput;
import com.brmgf.algafoodapi.domain.model.Estado;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EstadoInputDisassembler implements InputDisassembler<Estado, EstadoInput> {

    private final ModelMapper modelMapper;

    @Override
    public Estado toObjectModel(EstadoInput input) {
        return modelMapper.map(input, Estado.class);
    }
}
