package com.brmgf.algafoodapi.api.disassembler;

public interface InputDisassembler<T, R> {

    T toObjectModel(R input);
}
