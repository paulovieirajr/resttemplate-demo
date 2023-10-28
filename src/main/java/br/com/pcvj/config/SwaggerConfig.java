package br.com.pcvj.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${app.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basicScheme",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")))
                .info(new Info()
                        .title("Rest Template Consuming API")
                        .description("API developed using RestTemplate to consume external API")
                        .version(appVersion)
                        .license(new License()
                                .name("Apache 2.0"))
                        .contact(new Contact()
                                .name("Paulo Vieira")
                                .email("paulovieiradev@outlook.com")
                                .url("https://github.com/vieirajunior-90")));
    }
}
