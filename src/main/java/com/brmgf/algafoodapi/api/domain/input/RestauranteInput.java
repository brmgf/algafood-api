package com.brmgf.algafoodapi.api.domain.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteInput {

    @NotBlank
    private String nome;

    @PositiveOrZero
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdInput cozinha;

    @Valid
    @NotNull
    private EnderecoInput endereco;
}
