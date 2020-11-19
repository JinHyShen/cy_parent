package com.hong.servicebase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
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
    public Docket docket(){
        return new Docket( DocumentationType.SWAGGER_2)
//                .groupName ( "WebApi" )
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.hong"))
//                .paths ( Predicates.not (PathSelectors.regex ( "/admin/.*" )) )
//                .paths ( Predicates.not (PathSelectors.regex ( "/error/.*" )) )
//                .build();
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation( RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder ()
//                .title("xx学院")
//                .description("xx学院接口")
//                .contact(new Contact ("java","http://hong.com","xxx@163.com"))
                .version("1.0")
                .build();
    }
}
