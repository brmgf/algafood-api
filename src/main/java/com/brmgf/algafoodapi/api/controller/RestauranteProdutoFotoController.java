package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.domain.input.RestauranteProdutoFotoInput;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
@RestController
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                              @Valid RestauranteProdutoFotoInput input) throws IOException {
        var nomeArquivo = UUID.randomUUID().toString()
                .concat("_")
                .concat(input.getArquivo().getOriginalFilename());
        var uploadPath = Path.of("/Users/mileg/Documents", nomeArquivo);
        input.getArquivo().transferTo(uploadPath);
    }
}
