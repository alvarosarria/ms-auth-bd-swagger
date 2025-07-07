package com.perfunlandia.ms_auth_bd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio de Base de Datos de Autenticación")
                        .version("1.0")
                        .description("Documentación de la MS.")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Pergunlandia Team")
                                .url("https://www.tuejemplo.com")
                                .email("tu.email@ejemplo.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}


