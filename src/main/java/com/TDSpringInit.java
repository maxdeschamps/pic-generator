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
@EntityScan("com.tpspring.entities")
@EnableJpaRepositories("com.tpspring.repositories")
public class TDSpringInit extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TDSpringInit.class, args);
    }

    @Override
    protected SpringApplicationBuilder
    configure(SpringApplicationBuilder builder) {
        return builder.sources(TDSpringInit.class);
    }
}