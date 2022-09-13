package it.epicode.be.catalogolibri.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.catalogolibri.security.model.Role;
import it.epicode.be.catalogolibri.security.model.Roles;


public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(Roles role);
}
