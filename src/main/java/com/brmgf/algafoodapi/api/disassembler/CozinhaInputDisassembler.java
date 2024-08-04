package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.domain.input.CozinhaInput;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CozinhaInputDisassembler {

    private final ModelMapper modelMapper;

    public Cozinha toObjectModel(CozinhaInput cozinhaInput) {
        return modelMapper.map(cozinhaInput, Cozinha.class);
    }
}
