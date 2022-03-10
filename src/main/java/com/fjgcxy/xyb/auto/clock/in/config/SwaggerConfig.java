package com.fjgcxy.xyb.auto.clock.in.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
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
    public Docket ProductApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fjgcxy.xyb.auto.clock.in.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(productApiInfo());

    }


    private ApiInfo productApiInfo() {
        return new ApiInfoBuilder()
                .title("校友邦自动打卡平台")
                .description("API接口文档")
                .version("v1.0")
                .build();
    }
}
