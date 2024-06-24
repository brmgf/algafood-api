package com.brmgf.algafoodapi.domain.exceptionhandler;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeCadastradaException;
import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.EntidadeNaoEncontradaException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
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

        if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, statusCode, request);
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

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
        String detail = String.format("A propriedade '%s' não existe.", path);
        ApiError error = ApiError.createApiErrorBuilder(HttpStatus.valueOf(statusCode.value()), ApiErrorType.MENSAGEM_INCOMPREENSIVEL, detail).build();

        return handleExceptionInternal(ex, error, headers, statusCode, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, HttpStatus.valueOf(statusCode.value()), request);
        }

        return super.handleTypeMismatch(ex, headers, statusCode, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = String.format("O parâmetro de URL '%s' recebeu um valor inválido. Informe um valor compatível com o tipo %s.",
                ex.getName(), ex.getRequiredType().getSimpleName());
        ApiError error = ApiError.createApiErrorBuilder(status, ApiErrorType.PARAMETRO_INVALIDO, detail).build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
        ApiError error = ApiError.createApiErrorBuilder(HttpStatus.valueOf(statusCode.value()), ApiErrorType.RECURSO_NAO_ENCONTRADO, detail).build();

        return handleExceptionInternal(ex, error, headers, statusCode, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaughtException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = "Ocorreu um erro interno inesperado no sistema.";
        ex.printStackTrace();

        ApiError error = ApiError.createApiErrorBuilder(status, ApiErrorType.ERRO_SISTEMA, detail).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<ApiErrorField> fields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> ApiErrorField.builder()
                        .name(fieldError.getField())
                        .userMessage(fieldError.getDefaultMessage())
                        .build())
                .toList();

        ApiError error = ApiError.createApiErrorBuilder(HttpStatus.valueOf(statusCode.value()), ApiErrorType.DADOS_INVALIDOS, detail)
                .fields(fields)
                .build();

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
