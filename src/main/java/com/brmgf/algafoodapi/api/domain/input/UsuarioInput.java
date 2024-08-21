package com.brmgf.algafoodapi.api.domain.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioInput {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;
}
