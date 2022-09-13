package it.epicode.be.catalogolibri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.catalogolibri.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
	
	public List<Libro> findByTitolo(String titolo);
	public List<Libro> findByCategorieNomeCategoria(String Nomecategoria);
	public List<Libro> findByAutoriCognome(String cognome);

}
