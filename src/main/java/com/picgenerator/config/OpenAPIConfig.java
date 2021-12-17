package com.picgenerator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        License license = new License().name("Apache 2.0").url("https://springdoc.org/");
        Info info = new Info().title("Pic Generator REST API").version("v0.0.1").license(license);
        return new OpenAPI().info(info);
    }

    @Bean
    public GroupedOpenApi completeApi() {
        return GroupedOpenApi.builder().group("Complete").pathsToMatch("/**").build();
    }
}