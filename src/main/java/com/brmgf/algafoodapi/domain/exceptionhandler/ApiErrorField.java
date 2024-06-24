package com.brmgf.algafoodapi.domain.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiErrorField {

    private String name;
    private String userMessage;
}
