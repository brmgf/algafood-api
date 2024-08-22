package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.disassembler.utils.InputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.FormaPagamentoInput;
import com.brmgf.algafoodapi.domain.model.FormaPagamento;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FormaPagamentoInputDisassembler implements InputDisassembler<FormaPagamento, FormaPagamentoInput> {

    private final ModelMapper modelMapper;

    @Override
    public FormaPagamento toObjectModel(FormaPagamentoInput input) {
        return modelMapper.map(input, FormaPagamento.class);
    }
}
