package com.otobusbiletisatissistemi.repositories;
import com.otobusbiletisatissistemi.entities.Yolcu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YolcuRepository extends JpaRepository<Yolcu, Long> {
}

