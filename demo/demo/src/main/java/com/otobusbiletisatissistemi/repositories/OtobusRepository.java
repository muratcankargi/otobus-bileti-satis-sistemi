package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Otobusler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OtobusRepository extends JpaRepository<Otobusler, Long> {
    @Query("SELECT s FROM Otobusler s WHERE s.otobusPlaka = ?1")
    Optional<Otobusler> findOtobuslerByOtobusPlaka(String plaka);
}
