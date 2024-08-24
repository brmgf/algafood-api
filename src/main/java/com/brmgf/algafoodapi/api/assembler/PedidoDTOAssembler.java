package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.assembler.utils.DTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.PedidoDTO;
import com.brmgf.algafoodapi.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PedidoDTOAssembler implements DTOAssembler<PedidoDTO, Pedido> {

    private final ModelMapper modelMapper;

    @Override
    public PedidoDTO toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    @Override
    public List<PedidoDTO> toCollectionDTO(Collection<Pedido> pedidos) {
        return pedidos.stream().map(this::toDTO).toList();
    }
}
