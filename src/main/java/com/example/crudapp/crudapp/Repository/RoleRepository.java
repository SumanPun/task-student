package com.example.crudapp.crudapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crudapp.crudapp.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	//Optional<Role> findByName(String name);
	
}
