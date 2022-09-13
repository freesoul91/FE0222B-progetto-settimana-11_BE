package it.epicode.be.catalogolibri.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.service.LibroService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	
	
	@GetMapping(path = "/libri")
	//Se più di un ruolo deve poter accedere all'endpoint
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')") // Se un SOLO ruolo : @PreAuthorize("hasRole('ROLE_USER')")
	@Operation(description = "Lista di TUTTI i Libri presenti nel DB")
	public ResponseEntity<List<Libro>> findAll() {
		List<Libro> findAll = libroService.findAll(); 

		if (findAll != null) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/libro/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca Libro per id")
	public ResponseEntity<Libro> findById(@PathVariable(required = true) Integer id) {
		Optional<Libro> find = libroService.findById(id);

		if (find.isPresent()) { 
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/libro")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserimento Libro")
	public ResponseEntity<Libro> save(@Valid @RequestBody Libro libro, BindingResult result) {
		Libro save = libroService.save(libro);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/libro/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Update Libro per id Libro")
	public ResponseEntity<Libro> update(@PathVariable Integer id, @Valid @RequestBody Libro libro, BindingResult result) {
		Libro save = libroService.update(id, libro);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/libro/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Delete Libro per id del Libro")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		libroService.delete(id);
		return new ResponseEntity<>("Libro deleted", HttpStatus.OK);

	}
	
	@GetMapping(path = "/libro/cat/{categoria}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca Libri per categoria")
	public ResponseEntity<List<Libro>> findByCategorieNomeCategoria(@PathVariable(required = true) String categoria) {
		List<Libro> findCat = libroService.findByCategorie(categoria);

		if (findCat != null) { 
			return new ResponseEntity<>(findCat, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(path = "/libro/aut/{cognome}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca Libri per autore")
	public ResponseEntity<List<Libro>> findByAutoriCognome(@PathVariable(required = true) String cognome) {
		List<Libro> findAut = libroService.findByAutori(cognome);

		if (findAut != null) { 
			return new ResponseEntity<>(findAut, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	/*
	 * prevedere tracciati di esempio SOLO con lista ID Autore e Categoria
	 * nel Service ciclare su queste collections e per ogni autoreId fare il
	 * findByAutoreId
	 * per ogni Autore trovato aggiungerlo ad una collection
	 * fare il set della collection sul Libro
	 * 
	 */

}
