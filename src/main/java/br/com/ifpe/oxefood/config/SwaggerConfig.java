package br.com.ifpe.oxefood.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;

@Configuration //anotaçoes da classe
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OxeFood API")
                        .version("1.0")
                        .description("API do OxeFood")
                        .contact(new Contact()
                                .name("Aluno IFPE")
                                .email("aluno@discente.ifpe.edu.br")));
    }

    @Bean //anotaçoes da classe
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/api/**")
                .pathsToExclude("/error", "/actuator/**")
                .build();
    }
}



// SwaggerConfig é uma classe de configuração do Spring que integra a API com o Swagger/OpenAPI.
// Permite documentar automaticamente todos os endpoints da aplicação.
// Facilita testes no Swagger UI sem precisar escrever manualmente toda a documentação