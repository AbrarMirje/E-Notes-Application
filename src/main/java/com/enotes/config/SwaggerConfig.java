package com.enotes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI notesOpenApi(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("E-Notes Application")
                                .description("API documentation for E-Notes")
                                .contact(
                                        new Contact()
                                                .name("Abrar Mirje")
                                                .email("abrarmirje5@gmail.com")
                                )
                );
    }
}
