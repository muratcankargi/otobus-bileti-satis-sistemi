package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Biletler;
import com.otobusbiletisatissistemi.entities.Seferler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BiletRepository extends JpaRepository<Biletler, Long> {
    //1 seferde yalnızca 1 koltuğu birisi alabilir
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Biletler b WHERE" +
            " b.seferId = :seferId AND b.KoltukNo = :koltukNo")
    boolean existsBySeferIdAndKoltukNo(Long seferId, Integer koltukNo);

}
