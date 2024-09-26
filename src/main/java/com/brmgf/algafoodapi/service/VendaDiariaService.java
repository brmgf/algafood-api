package com.brmgf.algafoodapi.service;

import com.brmgf.algafoodapi.domain.filter.VendaDiariaFilter;
import com.brmgf.algafoodapi.domain.model.dto.VendaDiariaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VendaDiariaService {

    public List<VendaDiariaDTO> consultarVendasDiarias(VendaDiariaFilter filter) {
        //TODO: criar consulta agregada
        return null;
    }
}
