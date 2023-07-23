package com.example.crudapp.crudapp.Repository;

import com.example.crudapp.crudapp.Entity.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Integer> {

    @Query("select u from PromoCode u where u.code = :code ")
    Optional<PromoCode> findByCode(String code);
}
