package com.example.crudapp.crudapp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.crudapp.crudapp.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	Optional<Student> findByName(String username);
	
	@Query("FROM Student st")
	List<Student> getAllDtudentData();
}
