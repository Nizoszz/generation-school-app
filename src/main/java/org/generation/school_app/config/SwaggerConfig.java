package org.generation.school_app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(new Info()
                .title("Generation School API")
                .version("1.0")
                .description("This service is responsible for registering students at the school"));
    }
}
