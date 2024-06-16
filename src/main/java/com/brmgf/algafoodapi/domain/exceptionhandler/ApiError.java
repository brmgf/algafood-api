package com.brmgf.algafoodapi.domain.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ApiError {

    private Integer status;
    private String type;
    private String title;
    private String detail;

    public static ApiError.ApiErrorBuilder createApiErrorBuilder(HttpStatus status, ApiErrorType type, String detail) {
        return ApiError.builder()
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail);
    }
}
