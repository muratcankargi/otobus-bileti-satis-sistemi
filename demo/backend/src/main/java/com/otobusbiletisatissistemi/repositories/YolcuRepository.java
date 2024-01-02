package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Yolcular;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface YolcuRepository extends JpaRepository<Yolcular, Long> {
    @Query("SELECT s FROM Yolcular s WHERE s.email = ?1")
    Optional<Yolcular> findYolcularByEmail(String email);

    @Query("SELECT y FROM Yolcular y WHERE y.email = :email ")
    List<Yolcular> getYolcuByEmail(
            @Param("email") String email
    );

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Yolcular (ad,soyad,TCKimlik_no, Email, Cinsiyet) VALUES " +
            "(:ad,:soyad,:TCKimlikNo,:email,:cinsiyet)", nativeQuery = true)
    void createYolcu(@Param("ad") String ad,
                     @Param("soyad") String soyad,
                     @Param("TCKimlikNo") String tckimlikNo,
                     @Param("email") String email,
                     @Param("cinsiyet") String cinsiyet);
}

