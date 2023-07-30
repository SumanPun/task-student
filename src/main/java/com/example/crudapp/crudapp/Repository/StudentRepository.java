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

	//@Query(value = "select s.Id, s.email, s.name, r.name from Student s inner join users_role ur on s.Id = ur.user_id inner join Role r on ur.role_id = r.id")
	//@Query(value = "select s.Id, s.email, s.name, r.name from Student s inner join users_role ur on s.Id = ur.user_id inner join Role r on ur.role_id = r.id")
	//List<Object[]> getStudentWithRoles();
}
