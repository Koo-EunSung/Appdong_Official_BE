package com.appdong.webpage.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(appInfo());
    }

    private Info appInfo() {
        return new Info()
                .title("Appdong Official Website API Doc")
                .description("앱동 공식 웹사이트 API 명세서입니다.")
                .version("1.0.0");
    }
}
