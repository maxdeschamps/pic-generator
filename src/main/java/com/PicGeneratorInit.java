package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EntityScan("com.picgenerator.entities")
@EnableJpaRepositories("com.picgenerator.repositories")
public class PicGeneratorInit extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(PicGeneratorInit.class, args);
    }

    @Override
    protected SpringApplicationBuilder
    configure(SpringApplicationBuilder builder) {
        return builder.sources(PicGeneratorInit.class);
    }
}