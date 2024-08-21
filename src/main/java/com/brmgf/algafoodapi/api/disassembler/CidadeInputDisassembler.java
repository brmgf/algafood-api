package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.disassembler.utils.InputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.CidadeInput;
import com.brmgf.algafoodapi.domain.model.Cidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CidadeInputDisassembler implements InputDisassembler<Cidade, CidadeInput> {

    private final ModelMapper modelMapper;

    @Override
    public Cidade toObjectModel(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }
}
