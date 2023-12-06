package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Firmalar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirmaRepository extends JpaRepository<Firmalar, Long> {

    @Query("SELECT s FROM Firmalar s WHERE s.firmaIletisim = ?1")
    Optional<Firmalar> findFirmalarByFirmaIletisim(String email);
}
