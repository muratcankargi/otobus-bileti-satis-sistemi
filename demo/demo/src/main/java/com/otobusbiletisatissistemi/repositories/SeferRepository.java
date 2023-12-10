package com.otobusbiletisatissistemi.repositories;

import com.otobusbiletisatissistemi.dto.SeferBiletDTO;
import com.otobusbiletisatissistemi.entities.Biletler;
import com.otobusbiletisatissistemi.entities.Seferler;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Query("SELECT s.firma.firmaAdi, s.seferKalkisYeri,s.seferVarisYeri, s.seferKalkisSaati,s.seferVarisSaati,AVG(b.BiletFiyat),s.seferNo,MAX(b.KoltukNo)\n" +
            "FROM Biletler b\n" +
            "JOIN Seferler s ON b.seferId = s.seferNo\n" +
            "WHERE s.seferKalkisYeri = :nereden \n" +
            "  AND s.seferVarisYeri = :nereye \n" +
            "  AND s.seferKalkisSaati >= :startOfDay\n" +
            "  AND s.seferKalkisSaati <= :endOfDay" +
            " GROUP BY \n" +
            " s.firma.firmaAdi," +
            "  s.seferNo,\n" +
            "  s.seferKalkisYeri,\n" +
            "  s.seferKalkisSaati,\n" +
            "  s.seferVarisYeri," +
            "  s.seferVarisSaati\n")
    List<Object> getSeferAndBiletFiyat(
            @Param("nereden") String nereden,
            @Param("nereye") String nereye,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Seferler (firma_id, otobus_id, sefer_kalkis_yeri, sefer_varis_yeri, sefer_kalkis_saati, " +
            "sefer_varis_saati) VALUES (:otobusId, :firmaId, :seferKalkisYeri, :seferVarisYeri, :seferKalkisSaati," +
            " :seferVarisSaati)", nativeQuery = true)
    void createSefer(@Param("otobusId") Long otobusId,
                     @Param("firmaId") Long firmaId,
                     @Param("seferKalkisYeri") String seferKalkisYeri,
                     @Param("seferVarisYeri") String seferVarisYeri,
                     @Param("seferKalkisSaati") LocalDateTime seferKalkisSaati,
                     @Param("seferVarisSaati") LocalDateTime seferVarisSaati);
}
