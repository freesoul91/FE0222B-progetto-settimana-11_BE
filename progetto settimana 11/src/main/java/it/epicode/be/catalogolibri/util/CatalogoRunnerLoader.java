package it.epicode.be.catalogolibri.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.be.catalogolibri.model.Autore;
import it.epicode.be.catalogolibri.model.Categoria;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.repository.AutoreRepository;
import it.epicode.be.catalogolibri.repository.CategoriaRepository;
import it.epicode.be.catalogolibri.repository.LibroRepository;

@Component
public class CatalogoRunnerLoader implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private AutoreRepository autoreRepo;
	
	@Autowired
	private LibroRepository libroRepo;

	@Override
	public void run(String... args) throws Exception {
		// Lista categorie
		List<Categoria> categorie = initCategorie();
		Categoria catGiallo = categorie.get(0);
		categoriaRepo.save(catGiallo);
		Categoria catFantasy = categorie.get(1);
		categoriaRepo.save(catFantasy);
		Categoria catNoir = categorie.get(2);
		categoriaRepo.save(catNoir);
		Categoria catThriller = categorie.get(3);
		categoriaRepo.save(catThriller);
		// cats libro 1
		List<Categoria> categorieL1 = new ArrayList<>();
		categorieL1.add(catGiallo);
		// cats libro 2
		List<Categoria> categorieL2 = new ArrayList<>();
		categorieL2.add(catFantasy);
		// cats libro 3
		List<Categoria> categorieL3 = new ArrayList<>();
		categorieL3.add(catNoir);
		// cats libro 4
		List<Categoria> categorieL4 = new ArrayList<>();
		categorieL4.add(catThriller);
		// Lista autori
		List<Autore> autori = initAutori();
		Autore umberto = autori.get(0);
		autoreRepo.save(umberto);
		Autore coso = autori.get(1);
		autoreRepo.save(coso);
		Autore giorgio = autori.get(2);
		autoreRepo.save(giorgio);
		Autore tom = autori.get(3);
		autoreRepo.save(tom);
		//autori L1
		List<Autore> autoriL1 = new ArrayList<>();
		autoriL1.add(umberto);
		//autori L2
		List<Autore> autoriL2 = new ArrayList<>();
		autoriL2.add(coso);
		// autori L3
		List<Autore> autoriL3 = new ArrayList<>();
		autoriL3.add(giorgio);
		// autori L4
		List<Autore> autoriL4 = new ArrayList<>();
		autoriL4.add(tom);
		
		//Lista libri
		List<Libro> libri = initLibri();
		
		Libro l1 = libri.get(0);
		l1.setAutori(autoriL1);
		l1.setCategorie(categorieL1);
		libroRepo.save(l1);
		
		Libro l2 = libri.get(1);
		l2.setAutori(autoriL2);
		l2.setCategorie(categorieL2);
		libroRepo.save(l2);
		
		Libro l3 = libri.get(2);
		l3.setAutori(autoriL3);
		l3.setCategorie(categorieL3);
		libroRepo.save(l3);
		
		Libro l4 = libri.get(3);
		l4.setAutori(autoriL4);
		l4.setCategorie(categorieL4);
		libroRepo.save(l4);	
		
		
	}
	
	private List<Categoria> initCategorie() {
		List<Categoria> categorie = new ArrayList<>();
		Categoria giallo = new Categoria();
		giallo.setNomeCategoria("Giallo");
		Categoria fantasy = new Categoria();
		fantasy.setNomeCategoria("Fantasy");
		Categoria noir = new Categoria();
		noir.setNomeCategoria("Noir");
		Categoria thriller = new Categoria();
		thriller.setNomeCategoria("Thriller");
		categorie.add(giallo);
		categorie.add(fantasy);
		categorie.add(noir);
		categorie.add(thriller);
		return categorie;
	}
	
	private List<Autore> initAutori() {
		List<Autore> autori = new ArrayList<>();
		
		Autore a1 = new Autore();
		a1.setNome("Umberto");
		a1.setCognome("Eco");
		
		Autore a2 = new Autore();
		a2.setNome("Coso");
		a2.setCognome("Tolkien");
		
		Autore a3 = new Autore();
		a3.setNome("Giorgio");
		a3.setCognome("Rossi");
		
		Autore a4 = new Autore();
		a4.setNome("Tom");
		a4.setCognome("Clancy");
		
		autori.add(a1);
		autori.add(a2);
		autori.add(a3);
		autori.add(a4);
		
		return autori;
		
	}
	
	private List<Libro> initLibri() {
		List<Libro> libri = new ArrayList<>();
		
		Libro l1 = new Libro();
		l1.setTitolo("Il Nome della Rosa");
		l1.setAnno(1980);
		l1.setPrezzo(14.60);
		
		Libro l2 = new Libro();
		l2.setTitolo("Il Signore degli Anelli");
		l2.setAnno(1912);
		l2.setPrezzo(14.80);
		
		Libro l3 = new Libro();
		l3.setTitolo("In Cucina con Giorgio");
		l3.setAnno(2018);
		l3.setPrezzo(12.90);
		
		Libro l4 = new Libro();
		l4.setTitolo("Caccia a Ottobre Rosso");
		l4.setAnno(1992);
		l4.setPrezzo(15.00);
		
		libri.add(l1);
		libri.add(l2);
		libri.add(l3);
		libri.add(l4);
		
		return libri;
	}

}
