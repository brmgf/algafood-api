package com.brmgf.algafoodapi.api.assembler.utils;

import java.util.Collection;
import java.util.List;

public interface DTOAssembler<T, R> {

    T toDTO(R objectModel);

    List<T> toCollectionDTO(Collection<R> models);
}
