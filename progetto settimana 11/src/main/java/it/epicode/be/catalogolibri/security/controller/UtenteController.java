package it.epicode.be.catalogolibri.security.controller;

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
import it.epicode.be.catalogolibri.security.model.Utente;
import it.epicode.be.catalogolibri.security.service.UtenteService;


@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	@GetMapping(path = "/utenti")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Lista di TUTTI gli Utenti presenti nel DB")
	public ResponseEntity<List<Utente>> findAll() {
		List<Utente> findAll = utenteService.findAll();

		if (findAll != null) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/utente/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca Utente per id Utente")
	public ResponseEntity<Utente> findById(@PathVariable(required = true) Integer id) {
		Optional<Utente> find = utenteService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/utente")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserimento Utente (operazione consentita SOLO all'Admin)")
	public ResponseEntity<Utente> save(@RequestBody Utente utente) {
		Utente save = utenteService.save(utente);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/utente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Update Utente per id Utente (operazione consentita SOLO all'Admin)")
	public ResponseEntity<Utente> update(@PathVariable Integer id, @RequestBody Utente utente) {
		Utente save = utenteService.update(id, utente);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/utente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Delete Utente per id Utente (operazione consentita SOLO all'Admin)")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		utenteService.delete(id);
		return new ResponseEntity<>("Utente deleted", HttpStatus.OK);

	}

}
