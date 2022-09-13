package it.epicode.be.catalogolibri.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.catalogolibri.model.Categoria;
import it.epicode.be.catalogolibri.service.CategoriaService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class CategoriaController {
	
	//http://localhost:8080/swagger-ui.html indirizzo Per Swagger
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping(path = "/categorie")
	//Se più di un ruolo deve poter accedere all'endpoint
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')") // Se un SOLO ruolo : @PreAuthorize("hasRole('ROLE_USER')")
	@Operation(description = "Lista di TUTTE le Categorie presenti nel DB")
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> findAll = categoriaService.findAll();

		if (findAll != null) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/categoria/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca Categoria per id Categoria")
	public ResponseEntity<Categoria> findById(@PathVariable(required = true) Integer id) {
		Optional<Categoria> find = categoriaService.findById(id);

		if (find.isPresent()) { 
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/categoria")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserimento Categoria")
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
		Categoria save = categoriaService.save(categoria);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/categoria/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Update Categoria per id Categoria")
	public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria categoria) {
		Categoria save = categoriaService.update(id, categoria);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/categoria/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Delete Categoria per id Categoria")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return new ResponseEntity<>("Categoria deleted", HttpStatus.OK);

	}

}
