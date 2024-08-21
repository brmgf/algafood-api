package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.domain.input.EstadoInput;
import com.brmgf.algafoodapi.domain.model.Estado;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EstadoInputDisassembler implements InputDisassembler<Estado, EstadoInput> {

    private final ModelMapper modelMapper;

    public Estado toObjectModel(EstadoInput estadoInput) {
        return modelMapper.map(estadoInput, Estado.class);
    }
}
