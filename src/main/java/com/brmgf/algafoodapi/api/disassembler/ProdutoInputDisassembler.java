package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.disassembler.utils.InputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.ProdutoInput;
import com.brmgf.algafoodapi.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProdutoInputDisassembler implements InputDisassembler<Produto, ProdutoInput> {

    private final ModelMapper modelMapper;
    @Override
    public Produto toObjectModel(ProdutoInput input) {
        return modelMapper.map(input, Produto.class);
    }
}
