package com.example.crudapp.crudapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crudapp.crudapp.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
