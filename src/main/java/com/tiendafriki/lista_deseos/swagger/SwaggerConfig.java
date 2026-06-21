package com.tiendafriki.lista_deseos.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI listaDeseosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Lista de Deseos")
                        .description("API REST para la gestión de artículos deseados")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Coreplay")
                                .email("soporte@coreplay.com")));
    }
}