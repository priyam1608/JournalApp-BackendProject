package net.sharma.journalApp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerCustomConfig(){
        return new OpenAPI().info(
                new Info().title("Journal App").description("By Priyam Sharma")
        ).servers(Arrays.asList(
                new Server().url("http://localhost:8080").description("local"),
                new Server().url("http://localhost:8081").description("live")
                )
        ).tags(Arrays.asList(
                new Tag().name("Public APIs").description("Entry Point APIs for public"),
                new Tag().name("User APIs").description("User Authenticated APIs"),
                new Tag().name("Journal APIs").description("User authenticated CRUD APIs for Journal"),
                new Tag().name("Admin APIs").description("Admin Authenticated APIs")

        )).addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                        "bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                ));
    }
}
