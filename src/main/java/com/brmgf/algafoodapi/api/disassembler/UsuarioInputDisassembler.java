package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.disassembler.utils.InputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.UsuarioInput;
import com.brmgf.algafoodapi.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsuarioInputDisassembler implements InputDisassembler<Usuario, UsuarioInput> {

    private final ModelMapper modelMapper;

    @Override
    public Usuario toObjectModel(UsuarioInput input) {
        return modelMapper.map(input, Usuario.class);
    }
}
