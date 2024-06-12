package com.brmgf.algafoodapi.domain.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ApiError {

    private LocalDateTime dataHoraErro;
    private String mensagem;
}
