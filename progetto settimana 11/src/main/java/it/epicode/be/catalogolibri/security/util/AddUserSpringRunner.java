package it.epicode.be.catalogolibri.security.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.epicode.be.catalogolibri.security.model.Role;
import it.epicode.be.catalogolibri.security.model.Roles;
import it.epicode.be.catalogolibri.security.model.Utente;
import it.epicode.be.catalogolibri.security.repository.RoleRepository;
import it.epicode.be.catalogolibri.security.repository.UtenteRepository;


@Component
public class AddUserSpringRunner implements CommandLineRunner {

	@Autowired
	UtenteRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		// ruolo User
		Role roleU = new Role();
		roleU.setRoleName(Roles.ROLE_USER);
		// Ruolo Admin
		Role roleA = new Role();
		roleA.setRoleName(Roles.ROLE_ADMIN);
		
		// Utente "admin"
		Utente admin = new Utente();
		Set<Role> rolesA = new HashSet<>(); 
		rolesA.add(roleA);
		rolesA.add(roleU);
		admin.setUserName("admin");
		admin.setPassword(bCrypt.encode("admin"));
		admin.setEmail("admin@domain.com");
		admin.setRoles(rolesA);
		admin.setActive(true);
		
		//Utente "user"
		Utente user = new Utente();
		Set<Role> rolesU = new HashSet<>(); 
		rolesU.add(roleU);
		user.setUserName("user");
		user.setPassword(bCrypt.encode("user"));
		user.setEmail("user@gmail.com");
		user.setRoles(rolesU);
		user.setActive(true);
		
		// Salvataggio su DB
		roleRepository.save(roleU);
		roleRepository.save(roleA);
		userRepository.save(admin);
		userRepository.save(user);

	}

}
