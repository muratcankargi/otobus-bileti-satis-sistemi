package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Biletler;
import com.otobusbiletisatissistemi.entities.Seferler;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface BiletRepository extends JpaRepository<Biletler, Long> {
    //1 seferde yalnızca 1 koltuğu birisi alabilir
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Biletler b WHERE" +
            " b.seferId = :seferId AND b.KoltukNo = :koltukNo")
    boolean existsBySeferIdAndKoltukNo(Long seferId, Integer koltukNo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Biletler (Yolcu_id,Sefer_id,Koltuk_no,Bilet_fiyat,Satis_Tarihi)" +
            "VALUES (:yolcuId,:seferId,:koltukNo,:biletFiyat,:satisTarihi)", nativeQuery = true)
    void createBilet(@Param("yolcuId") Long yolcuId,
                     @Param("seferId") Long seferId,
                     @Param("koltukNo") Integer koltukNo,
                     @Param("biletFiyat") Integer biletFiyat,
                     @Param("satisTarihi") Date satisTarihi);

}
