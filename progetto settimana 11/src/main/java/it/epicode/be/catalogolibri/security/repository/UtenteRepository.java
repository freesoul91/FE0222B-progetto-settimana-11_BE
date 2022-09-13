package it.epicode.be.catalogolibri.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.catalogolibri.security.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

	public Optional<Utente> findById(Integer id);
	public Optional<Utente> findByUserName(String userName);
	public boolean existsByEmail(String email);
	public boolean existsByUserName(String userName);
}
