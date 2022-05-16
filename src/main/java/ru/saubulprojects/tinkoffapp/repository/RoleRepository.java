package ru.saubulprojects.tinkoffapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.saubulprojects.tinkoffapp.model.Role;
import ru.saubulprojects.tinkoffapp.model.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(RoleType name);
	
}
