package com.brmgf.algafoodapi.domain.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ApiError {

    private Integer status;
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private List<ApiErrorField> fields;

    public static ApiError.ApiErrorBuilder createApiErrorBuilder(HttpStatus status, ApiErrorType type, String detail) {
        return ApiError.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail);
    }
}
