package com.example.crudapp.crudapp.Services;

import com.example.crudapp.crudapp.Entity.RefreshToken;
import com.example.crudapp.crudapp.Entity.Student;
import com.example.crudapp.crudapp.Exceptions.RefreshTokenException;
import com.example.crudapp.crudapp.Exceptions.ResourceNotFoundException;
import com.example.crudapp.crudapp.Repository.RefreshTokenRepository;
import com.example.crudapp.crudapp.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private long refreshTokenDuration = 24*60*60*60*1000;
    private final RefreshTokenRepository refreshTokenRepository;
    private final StudentRepository studentRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, StudentRepository studentRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.studentRepository = studentRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefrehToken(Integer studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("student","studentId",studentId));
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setStudent(student);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenDuration));
        this.refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token.getExpiryDate().compareTo(LocalDateTime.now()) < 0) {
            this.refreshTokenRepository.delete(token);
            throw new RefreshTokenException(token.getToken(),"refresh token is invalid! please make a new sing in request");
        }
        return token;
    }

    @Transactional
    public int deleteByUser(int studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("student","studentId",studentId));
        return this.refreshTokenRepository.deleteByStudent(student);
    }
}
