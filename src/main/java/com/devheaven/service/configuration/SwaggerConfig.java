package com.devheaven.service.configuration;

import com.devheaven.service.models.Error;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * This class configures the Swagger UI & API.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final TypeResolver typeResolver;

    @Autowired
    public SwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    /**
     * Configures the Swagger API.
     *
     * @return Docket with swagger information.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.devheaven.service"))
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(metadata())
                .additionalModels(typeResolver.resolve(Error.class))
                .useDefaultResponseMessages(false)
                .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                .produces(new HashSet<String>() {{
                    add("application/json");
                }})
                .consumes(new HashSet<String>() {{
                    add("application/json");
                }});
    }

    /**
     * Configures the meta data for the Swagger API.
     *
     * @return ApiInfoBuild with details about the application.
     */
    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Project Management")
                .description("Project Management API for the DevHeaven platform")
                .version("1.0.0")
                .contact(new Contact("DevHeaven", "http://devheaven.nl", "devheavenplatform@gmail.com"))
                .build();
    }

}
