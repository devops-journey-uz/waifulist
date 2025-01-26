package zawkin.me.asuna.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Bean for custom OpenAPI info (metadata)
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Waifu Management API")
                        .version("1.0")
                        .description("API for managing waifu entries in the system")
                );
    }

    // Bean for grouping public API
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**") // Matches all API paths, adjust to match specific groups if needed
                .build();
    }
}
