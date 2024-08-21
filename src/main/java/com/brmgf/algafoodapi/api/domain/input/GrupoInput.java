package com.brmgf.algafoodapi.api.domain.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoInput {

    @NotBlank
    private String nome;
}
