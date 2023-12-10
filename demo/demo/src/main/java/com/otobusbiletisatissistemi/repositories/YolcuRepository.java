package com.otobusbiletisatissistemi.repositories;
import com.otobusbiletisatissistemi.entities.Yolcular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface YolcuRepository extends JpaRepository<Yolcular, Long> {
    @Query("SELECT s FROM Yolcular s WHERE s.email = ?1")
    Optional<Yolcular> findYolcularByEmail(String email);

    @Query("SELECT y FROM Yolcular y WHERE y.email = :email ")
    List<Yolcular> getYolcuByEmail(
        @Param("email") String email
    );
}

