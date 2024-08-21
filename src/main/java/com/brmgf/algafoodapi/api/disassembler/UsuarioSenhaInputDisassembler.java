package com.brmgf.algafoodapi.api.disassembler;

import com.brmgf.algafoodapi.api.disassembler.utils.InputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.UsuarioSenhaInput;
import com.brmgf.algafoodapi.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsuarioSenhaInputDisassembler implements InputDisassembler<Usuario, UsuarioSenhaInput> {

    private final ModelMapper modelMapper;

    @Override
    public Usuario toObjectModel(UsuarioSenhaInput input) {
        return modelMapper.map(input, Usuario.class);
    }
}
