package com.brmgf.algafoodapi.api.domain.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoIdInput {

    @Schema(example = "6", required = true)
    @NotNull
    private Long id;
}
