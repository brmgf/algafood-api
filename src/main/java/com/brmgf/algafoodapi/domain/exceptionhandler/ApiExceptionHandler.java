package com.brmgf.algafoodapi.domain.exceptionhandler;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeCadastradaException;
import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.EntidadeNaoEncontradaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.util.Objects.isNull;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError error = createApiErrorBuilder(status, ApiErrorType.ENTIDADE_NAO_ENCONTRADA, ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CampoObrigatorioException.class)
    public ResponseEntity<?> handleCampoObrigatorioException(CampoObrigatorioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = createApiErrorBuilder(status, ApiErrorType.CAMPO_OBRIGATORIO, ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ApiError error = createApiErrorBuilder(status, ApiErrorType.ENTIDADE_EM_USO, ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeCadastradaException.class)
    public ResponseEntity<?> handleEntidadeCadastradaException(EntidadeCadastradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ApiError error = createApiErrorBuilder(status, ApiErrorType.ENTIDADE_CADASTRADA, ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = createApiErrorBuilder(status, ApiErrorType.REGRA_NEGOCIO, ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (isNull(body)) {
            body = ApiError.builder()
                    .title(statusCode.toString())
                    .status(statusCode.value())
                    .build();
        } else if (body instanceof String) {
            body = ApiError.builder()
                    .title((String) body)
                    .status(statusCode.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ApiError.ApiErrorBuilder createApiErrorBuilder(HttpStatus status, ApiErrorType type, String detail) {
        return ApiError.builder()
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail);
    }
}
