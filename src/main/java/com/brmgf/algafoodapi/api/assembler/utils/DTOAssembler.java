package com.brmgf.algafoodapi.api.assembler.utils;

import java.util.List;

public interface DTOAssembler<T, R> {

    T toDTO(R objectModel);

    List<T> toCollectionDTO(List<R> models);
}
