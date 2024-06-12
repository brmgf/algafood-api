package com.brmgf.algafoodapi.domain.exceptionhandler;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeCadastradaException;
import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
        ApiError apiError = ApiError.builder()
                .dataHoraErro(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(apiError);
    }

    @ExceptionHandler({NegocioException.class, CampoObrigatorioException.class})
    public ResponseEntity<?> tratarNegocioException(NegocioException e) {
        ApiError apiError = ApiError.builder()
                .dataHoraErro(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }

    @ExceptionHandler({EntidadeEmUsoException.class, EntidadeCadastradaException.class})
    public ResponseEntity<?> tratarEntidadeEmUsoException(NegocioException e) {
        ApiError apiError = ApiError.builder()
                .dataHoraErro(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(apiError);
    }
}
