package com.example.projetjeespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProjetJeeSpringBootApplication extends SpringBootServletInitializer {
    @Override
    // Configuring method has to be overridden
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(ProjetJeeSpringBootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjetJeeSpringBootApplication.class, args);
    }

}
