package cl.dgac.drones.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI dgacDronesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API DGAC - Microservicio de Drones")
                        .description("Documentación oficial de los endpoints para el registro y control de aeronaves no tripuladas (drones) en el sistema DGAC.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo DGAC")
                                .email("soporte@dgac.cl")
                                .url("https://www.dgac.cl")));
    }
}