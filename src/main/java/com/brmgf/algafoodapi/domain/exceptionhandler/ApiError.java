package com.brmgf.algafoodapi.domain.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ApiError {

    private Integer status;
    private String type;
    private String title;
    private String detail;
}
