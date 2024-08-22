package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.disassembler.utils.InputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.GrupoInput;
import com.brmgf.algafoodapi.domain.model.Grupo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GrupoInputDisassembler implements InputDisassembler<Grupo, GrupoInput> {

    private final ModelMapper modelMapper;

    @Override
    public Grupo toObjectModel(GrupoInput input) {
        return modelMapper.map(input, Grupo.class);
    }
}
