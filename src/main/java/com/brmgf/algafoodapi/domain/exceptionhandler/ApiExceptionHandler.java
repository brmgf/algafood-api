package com.brmgf.algafoodapi.domain.exceptionhandler;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeCadastradaException;
import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.EntidadeNaoEncontradaException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError error = ApiError.createApiErrorBuilder(status, ApiErrorType.ENTIDADE_NAO_ENCONTRADA, ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CampoObrigatorioException.class)
    public ResponseEntity<?> handleCampoObrigatorioException(CampoObrigatorioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = ApiError.createApiErrorBuilder(status, ApiErrorType.CAMPO_OBRIGATORIO, ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ApiError error = ApiError.createApiErrorBuilder(status, ApiErrorType.ENTIDADE_EM_USO, ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeCadastradaException.class)
    public ResponseEntity<?> handleEntidadeCadastradaException(EntidadeCadastradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ApiError error = ApiError.createApiErrorBuilder(status, ApiErrorType.ENTIDADE_CADASTRADA, ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = ApiError.createApiErrorBuilder(status, ApiErrorType.REGRA_NEGOCIO, ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatExcetion((InvalidFormatException) rootCause, headers, statusCode, request);
        }

        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
        ApiError error = ApiError.createApiErrorBuilder(HttpStatus.valueOf(statusCode.value()), ApiErrorType.MENSAGEM_INCOMPREENSIVEL, detail).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), statusCode, request);
    }

    private ResponseEntity<Object> handleInvalidFormatExcetion(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
        String detail = String.format("A propriedade '%s' recebeu um tipo inválido. Informe um valor compatível com o tipo %s.",
                path, ex.getTargetType().getSimpleName());
        ApiError error = ApiError.createApiErrorBuilder(HttpStatus.valueOf(statusCode.value()), ApiErrorType.MENSAGEM_INCOMPREENSIVEL, detail).build();

        return handleExceptionInternal(ex, error, headers, statusCode, request);
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
}
