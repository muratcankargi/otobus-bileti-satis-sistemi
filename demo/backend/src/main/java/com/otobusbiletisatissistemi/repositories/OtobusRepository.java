package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Otobusler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OtobusRepository extends JpaRepository<Otobusler, Long> {
    @Query("SELECT s FROM Otobusler s WHERE s.otobusPlaka = ?1")
    Optional<Otobusler> findOtobuslerByOtobusPlaka(String plaka);

    @Query("SELECT o FROM Otobusler o WHERE o.firmaId = :firmaId")
    List<Otobusler> getOtobusBySeferId(@Param("firmaId") Long seferId);
}
