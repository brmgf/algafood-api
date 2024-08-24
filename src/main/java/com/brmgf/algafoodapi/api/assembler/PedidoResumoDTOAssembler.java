package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.assembler.utils.DTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.PedidoResumoDTO;
import com.brmgf.algafoodapi.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PedidoResumoDTOAssembler implements DTOAssembler<PedidoResumoDTO, Pedido> {

    private final ModelMapper modelMapper;

    @Override
    public PedidoResumoDTO toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    @Override
    public List<PedidoResumoDTO> toCollectionDTO(Collection<Pedido> pedidos) {
        return pedidos.stream().map(this::toDTO).toList();
    }
}
