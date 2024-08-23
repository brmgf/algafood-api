package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.assembler.utils.DTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.PermissaoDTO;
import com.brmgf.algafoodapi.domain.model.Permissao;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PermissaoDTOAssembler implements DTOAssembler<PermissaoDTO, Permissao> {

    private final ModelMapper modelMapper;

    @Override
    public PermissaoDTO toDTO(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoDTO.class);
    }

    @Override
    public List<PermissaoDTO> toCollectionDTO(Collection<Permissao> permissoes) {
        return permissoes.stream().map(this::toDTO).toList();
    }
}
