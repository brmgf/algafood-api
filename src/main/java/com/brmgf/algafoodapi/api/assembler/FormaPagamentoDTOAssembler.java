package com.brmgf.algafoodapi.api.assembler;

import com.brmgf.algafoodapi.api.assembler.utils.DTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.FormaPagamentoDTO;
import com.brmgf.algafoodapi.domain.model.FormaPagamento;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FormaPagamentoDTOAssembler implements DTOAssembler<FormaPagamentoDTO, FormaPagamento> {

    private final ModelMapper modelMapper;

    @Override
    public FormaPagamentoDTO toDTO(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }

    @Override
    public List<FormaPagamentoDTO> toCollectionDTO(Collection<FormaPagamento> formasPagamento) {
        return formasPagamento.stream().map(this::toDTO).toList();
    }
}
