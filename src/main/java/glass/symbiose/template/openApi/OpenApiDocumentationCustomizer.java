package glass.symbiose.template.openApi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "",
                version = "0.0.1",
                description = ""),
        tags = {})
@Configuration
public class OpenApiDocumentationCustomizer {
    @Bean
    public OpenApiCustomizer schemaCustomizer() {
         return openApi -> {};
    }
    @Bean
    public OpenApiCustomizer requestBodyExamplesCustomizer() {
        return openApi -> {};
    }
    @Bean
    public OpenApiCustomizer responseExamplesCustomizer() {
        return openApi -> {};
    }
}
