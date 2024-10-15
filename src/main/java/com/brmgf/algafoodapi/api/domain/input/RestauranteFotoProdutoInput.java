package com.brmgf.algafoodapi.api.domain.input;

import com.brmgf.algafoodapi.core.validator.FileContentType;
import com.brmgf.algafoodapi.core.validator.FileSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class RestauranteFotoProdutoInput {

    @FileSize(max = "1MB")
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    @NotNull
    private MultipartFile arquivo;

    @NotNull
    @NotBlank
    private String descricao;
}
