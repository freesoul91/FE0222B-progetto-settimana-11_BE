package it.epicode.be.catalogolibri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.catalogolibri.model.Autore;

public interface AutoreRepository extends JpaRepository<Autore, Integer> {
	
	public Optional<Autore> findById(Integer id);
	public Optional<Autore> findByCognome(String cognome);
	public boolean existsByCognome(String cognome);
	List<Autore> findByNome(String nome);

}
