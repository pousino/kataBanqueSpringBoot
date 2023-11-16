package com.example.katabanquespringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;


@EnableSwagger2
public class SwaggerConfig {
    
    
    public static final String COMPTE_BANCAIRE = "Kata Compte Bancaire API";
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.katabanquespringboot.web"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(COMPTE_BANCAIRE, "Faites des depots et des retraits, consultez votre solde et consultez votre historique des operations bancaires."));
        
    }
    
}
