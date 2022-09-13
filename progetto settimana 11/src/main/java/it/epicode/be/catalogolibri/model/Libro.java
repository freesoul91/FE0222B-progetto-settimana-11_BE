package it.epicode.be.catalogolibri.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@JsonProperty("id")
	private Integer id;	
	private @NotBlank(message = "Inserire il titolo del libro!") String titolo;
	private @NotNull(message = "Inserire l'anno del libro!") Integer anno;
	private @NotNull(message = "Inserire il prezzo del libro!") Double prezzo;
	
	@ManyToMany
	@JoinTable(name = "libro_autore",
	joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "autore_id", referencedColumnName = "id"))
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<Autore> autori = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "libro_categoria", 
	joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id"))
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<Categoria> categorie = new ArrayList<>();

}
