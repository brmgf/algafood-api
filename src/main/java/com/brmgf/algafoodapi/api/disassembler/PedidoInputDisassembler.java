package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.disassembler.utils.InputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.PedidoInput;
import com.brmgf.algafoodapi.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PedidoInputDisassembler implements InputDisassembler<Pedido, PedidoInput> {

    private final ModelMapper modelMapper;

    @Override
    public Pedido toObjectModel(PedidoInput input) {
        return modelMapper.map(input, Pedido.class);
    }
}
