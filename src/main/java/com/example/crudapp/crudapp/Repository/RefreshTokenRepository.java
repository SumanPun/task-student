package com.example.crudapp.crudapp.Repository;

import com.example.crudapp.crudapp.Entity.RefreshToken;
import com.example.crudapp.crudapp.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByStudent(Student student);
}
