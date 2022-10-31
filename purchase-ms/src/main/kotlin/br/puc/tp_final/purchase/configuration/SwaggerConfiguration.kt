package br.puc.tp_final.purchase.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {
    @Bean
    fun customOpenAPI(@Value("\${springdoc.version}") springDocVersion: String?): OpenAPI? {
        return OpenAPI()
            .info(
                Info().title("Purchase API")
                    .version(springDocVersion)
                    .contact(
                        Contact().name("Caio Campos, Daniel Spiegel, Eduardo Augusto Lima Pereira, Kauane Cordeiro, Luan Pedro Tomiozzo, Paulo Henrique Borges Pereira, Rafael Sanzio Lopes Rocha, Ricardo Silva Pereira")
                            .url("https://github.com/Edd002/purchase-ms")
                    )
                    .description("Este é um exemplo de um microserviço de compras utilizando Kotlin e Spring Boot")
                    .termsOfService("http://swagger.io/terms")
                    .license(License().name("Open API 3 - Documentation").url("http://springdoc.org"))
            )
    }
}