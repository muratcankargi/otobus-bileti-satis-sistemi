package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Biletler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiletRepository extends JpaRepository<Biletler, Long> {
}
