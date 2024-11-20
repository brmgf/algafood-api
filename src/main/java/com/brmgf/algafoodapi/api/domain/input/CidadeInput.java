package com.brmgf.algafoodapi.api.domain.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeInput {

    @Schema(example = "Dores do Indaiá", required = true)
    @NotBlank
    private String nome;

    private EstadoIdInput estado;
}
