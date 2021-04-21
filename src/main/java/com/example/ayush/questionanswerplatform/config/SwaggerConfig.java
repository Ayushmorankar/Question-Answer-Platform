package com.example.ayush.questionanswerplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getQuestionAnswerPlatformApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/v1/**"))
                .apis(RequestHandlerSelectors.basePackage("com.example.ayush.questionanswerplatform"))
                .build()
                .apiInfo(getApiDetails());
    }

    private ApiInfo getApiDetails(){
        return new ApiInfoBuilder()
                .title("Question Answer Platform Documentation")
                .build();
    }

}
