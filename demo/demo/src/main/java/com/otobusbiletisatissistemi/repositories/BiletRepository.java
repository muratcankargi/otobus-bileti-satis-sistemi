package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Bilet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiletRepository extends JpaRepository<Bilet, Long> {
}
