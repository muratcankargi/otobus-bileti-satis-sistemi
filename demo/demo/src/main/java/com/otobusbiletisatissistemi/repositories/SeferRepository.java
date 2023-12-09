package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.entities.Seferler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SeferRepository extends JpaRepository<Seferler,Long> {
    @Query("SELECT s FROM Seferler s WHERE s.seferKalkisYeri = :nereden AND s.seferVarisYeri = :nereye " +
            "AND s.seferKalkisSaati >= :startDate AND s.seferKalkisSaati <= :endDate")

    List<Seferler> getSeferlerByKriter(
            @Param("nereden") String nereden,
            @Param("nereye") String nereye,
            @Param("startDate") LocalDateTime startOfDay,
            @Param("endDate") LocalDateTime endOfDay
    );

    /*@Query("SELECT b.id AS Bilet_id, b.yolcuId, b.seferId, b.KoltukNo, b.BiletFiyat, b.SatisTarihi\n" +
            "FROM Biletler b\n" +
            "JOIN Seferler s ON b.seferId = s.seferNo\n" +
            "WHERE s.seferKalkisYeri = :nereden \n" +
            "  AND s.seferVarisYeri = :nereye \n" +
            "  AND s.seferKalkisYeri >= :tarih\n" +
            "  AND s.seferKalkisSaati <= :tarih")
    List<Seferler> getSeferAndBiletFiyat(
            @Param("nereden") String nereden,
            @Param("nereye") String nereye,
            @Param("tarih") LocalDateTime dateTime
    );*/
}
