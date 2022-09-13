package it.epicode.be.catalogolibri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.catalogolibri.model.Categoria;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.repository.CategoriaRepository;
import it.epicode.be.catalogolibri.repository.LibroRepository;
import it.epicode.be.catalogolibri.security.exceptions.CatalogoException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private LibroRepository libroRepo;
	
	public Optional<Categoria> findById( Integer id) {
		return categoriaRepo.findById(id);
	}
	
	public List<Categoria> findByNomeCategoria(String nomeCategoria) {
		return categoriaRepo.findByNomeCategoria(nomeCategoria);
	}

	public List<Categoria> findAll() {
		return categoriaRepo.findAll();
	}

	public Categoria save(Categoria categoria) {
		return categoriaRepo.save(categoria);
	}

	public Categoria update(Integer id, Categoria categoria) {
		Optional<Categoria> categoriaResult = categoriaRepo.findById(id);

		if (categoriaResult.isPresent()) {
			Categoria categoriaUpdate = categoriaResult.get();
			categoriaUpdate.setNomeCategoria(categoria.getNomeCategoria());
			//categoriaUpdate.setLibri(categoria.getLibri());	
			categoriaRepo.save(categoriaUpdate);
			return categoriaUpdate;
		} else {
			throw new CatalogoException("Categoria non aggiornata");
		}

	}
	
	public void delete(Integer id) {
		Categoria c = categoriaRepo.findById(id).get();
		List<Libro> listaLibriCat = libroRepo.findByCategorieNomeCategoria(c.getNomeCategoria());
		for (Libro l : listaLibriCat ) {
			l.getCategorie().remove(c);
			libroRepo.save(l);
		}
		categoriaRepo.deleteById(id);
	}

}
