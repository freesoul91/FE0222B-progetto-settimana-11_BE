package it.epicode.be.catalogolibri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Catalogo Libri API", version = "1.0", description = "Gestionale Catalogo Libri CRUD con autenticazione/autorizzazione"))
public class CatalogoLibriApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoLibriApplication.class, args);
	}

}
