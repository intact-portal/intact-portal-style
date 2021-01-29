package uk.ac.ebi.intact.style.ws;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.*;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class StyleSwaggerConfig {
    private final TypeResolver typeResolver = new TypeResolver();

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(new Tag("legend", "Legend related"),
                        new Tag("interactor", "Interactor related"),
                        new Tag("interaction", "Interaction related"))
                .alternateTypeRules( AlternateTypeRules.newRule(
                        typeResolver.resolve(Color.class),
                        typeResolver.resolve(String.class), Ordered.HIGHEST_PRECEDENCE))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(Predicates.not(PathSelectors.regex("/")))
                .paths(Predicates.not(PathSelectors.regex("/update.*")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "IntAct Style REST API",
                "This API allow retrieving visual properties associated with IntAct interactor and interactions properties.",
                "API 1.0 BETA",
                "https://www.ebi.ac.uk/about/terms-of-use",
                new Contact("IntAct", "https://www.ebi.ac.uk/intact", "intact@helpdesk.ebi.ac.uk"),
                "License of API", "https://creativecommons.org/licenses/by/4.0/", Collections.emptyList());
    }
}