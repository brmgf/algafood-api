package com.brmgf.algafoodapi.api.disassembler.utils;

public interface InputDisassembler<T, R> {

    T toObjectModel(R input);
}
