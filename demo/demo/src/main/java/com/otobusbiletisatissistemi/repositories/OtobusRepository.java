package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Otobusler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtobusRepository extends JpaRepository<Otobusler, Long> {
}
