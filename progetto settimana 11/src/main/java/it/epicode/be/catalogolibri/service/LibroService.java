package it.epicode.be.catalogolibri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.repository.LibroRepository;
import it.epicode.be.catalogolibri.security.exceptions.CatalogoException;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepo;
	
	public Optional<Libro> findById( Integer id) {
		return libroRepo.findById(id);
	}
	
	public List<Libro> findByTitolo(String titolo) {
		return libroRepo.findByTitolo(titolo);
	}

	public List<Libro> findAll() {
		return libroRepo.findAll();
	}

	public Libro save(Libro libro) {
		return libroRepo.save(libro);
	}

	public Libro update(Integer id, Libro libro) {
		Optional<Libro> libroResult = libroRepo.findById(id);

		if (libroResult.isPresent()) {
			Libro libroUpdate = libroResult.get();
			libroUpdate.setTitolo(libro.getTitolo());
			libroUpdate.setPrezzo(libro.getPrezzo());
			libroUpdate.setAutori(libro.getAutori());
			libroUpdate.setAnno(libro.getAnno());
			libroUpdate.setCategorie(libro.getCategorie());
			libroUpdate.setAutori(libro.getAutori());
			libroRepo.save(libroUpdate);
			return libroUpdate;
		} else {
			throw new CatalogoException("Libro non aggiornato");
		}

	}
	
	public void delete(Integer id) {
		libroRepo.deleteById(id);
	}
	
	public List<Libro> findByCategorie(String categoria) {
		return libroRepo.findByCategorieNomeCategoria(categoria);
	}
	
	public List<Libro> findByAutori(String cognome) {
		return libroRepo.findByAutoriCognome(cognome);
	}

}
