package com.brmgf.algafoodapi.core.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Algafood API")
                    .description("API desenvolvida no curso Especialista Spring Rest da Algaworks.")
                    .contact(new Contact().name("Milene").url("https://github.com/brmgf").email("milenefaria33#gmail.com"))
        );
    }
}

