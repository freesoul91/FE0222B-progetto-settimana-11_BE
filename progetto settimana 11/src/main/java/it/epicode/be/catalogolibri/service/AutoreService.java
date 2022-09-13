package it.epicode.be.catalogolibri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.catalogolibri.model.Autore;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.repository.AutoreRepository;
import it.epicode.be.catalogolibri.repository.LibroRepository;
import it.epicode.be.catalogolibri.security.exceptions.CatalogoException;

@Service
public class AutoreService {
	
	@Autowired
	private AutoreRepository autoreRepo;
	
	@Autowired
	private LibroRepository libroRepo;
	
	public Optional<Autore> findById( Integer id) {
		return autoreRepo.findById(id);
	}
	
	public List<Autore> findByNome(String nome) {
		return autoreRepo.findByNome(nome);
	}

	public List<Autore> findAll() {
		return autoreRepo.findAll();
	}

	public Autore save(Autore autore) {
		return autoreRepo.save(autore);
	}

	public Autore update(Integer id, Autore autore) {
		Optional<Autore> autoreResult = autoreRepo.findById(id);

		if (autoreResult.isPresent()) {
			Autore autoreUpdate = autoreResult.get();
			autoreUpdate.setNome(autore.getNome());
			autoreUpdate.setCognome(autore.getCognome());
			//autoreUpdate.setLibri(autore.getLibri());
			autoreRepo.save(autoreUpdate);
			return autoreUpdate;
		} else {
			throw new CatalogoException("Autore non aggiornato");
		}

	}
	
	public void delete(Integer id) {
		Autore a = autoreRepo.findById(id).get();
		List<Libro> listaLibriCat = libroRepo.findByAutoriCognome(a.getCognome());
		for (Libro l : listaLibriCat ) {
			l.getAutori().remove(a);
			libroRepo.save(l);
		}
		autoreRepo.deleteById(id);
	}

}
