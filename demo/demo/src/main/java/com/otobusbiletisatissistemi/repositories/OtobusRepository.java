package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Otobus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtobusRepository extends JpaRepository<Otobus, Long> {
}
