package it.epicode.be.catalogolibri.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.catalogolibri.security.exceptions.CatalogoException;
import it.epicode.be.catalogolibri.security.model.Utente;
import it.epicode.be.catalogolibri.security.repository.UtenteRepository;


@Service
public class UtenteService {

	@Autowired
	UtenteRepository utenteRepository;

	public Optional<Utente> findById(Integer id) {
		return utenteRepository.findById(id);
	}

	public Optional<Utente> findByUserName(String username) {
		return utenteRepository.findByUserName(username);
	}

	public List<Utente> findAll() {
		return utenteRepository.findAll();
	}

	public Utente save(Utente utente) {
		return utenteRepository.save(utente);
	}

	public Utente update(Integer id, Utente utente) {
		Optional<Utente> utenteResult = utenteRepository.findById(id);

		if (utenteResult.isPresent()) {
			Utente utenteUpdate = utenteResult.get();
			
			utenteUpdate.setUserName(utente.getUserName());
			utenteUpdate.setEmail(utente.getEmail());
			utenteUpdate.setPassword(utente.getPassword());
			utenteUpdate.setActive(utente.isActive());
			utenteRepository.save(utenteUpdate);
			return utenteUpdate;
		} else {
			throw new CatalogoException("Utente non aggiornato");
		}

	}

	public void delete(Integer id) {
		utenteRepository.deleteById(id);
	}

}
