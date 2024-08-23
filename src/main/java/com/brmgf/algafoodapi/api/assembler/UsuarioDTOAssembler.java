package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.assembler.utils.DTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.UsuarioDTO;
import com.brmgf.algafoodapi.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UsuarioDTOAssembler implements DTOAssembler<UsuarioDTO, Usuario> {

    private final ModelMapper modelMapper;

    @Override
    public UsuarioDTO toDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Override
    public List<UsuarioDTO> toCollectionDTO(Collection<Usuario> usuarios) {
        return usuarios.stream().map(this::toDTO).toList();
    }
}
