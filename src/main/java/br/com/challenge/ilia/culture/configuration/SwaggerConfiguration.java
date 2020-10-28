package br.com.challenge.ilia.culture.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.challenge.ilia.culture"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, Arrays.asList(
                        new ResponseBuilder()
                                .code("500")
                                .description("An error has occurred")
                                .build(),
                        new ResponseBuilder()
                                .code("403")
                                .description("You don't have permission to access this resource")
                                .build()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Challenge Ília Culture",
                "",
                "Versão 1.0",
                "",
                new Contact("Cássio Danilo dos Santos Cardoso", "", "cassio.crow@gmail.com"), "", "",
                Collections.emptyList()

        );
    }

}
