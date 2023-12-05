package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Sefer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeferRepository extends JpaRepository<Sefer,Long> {
}
