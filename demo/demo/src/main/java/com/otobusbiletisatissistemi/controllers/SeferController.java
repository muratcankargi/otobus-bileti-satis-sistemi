package com.otobusbiletisatissistemi.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.otobusbiletisatissistemi.entities.Seferler;
import com.otobusbiletisatissistemi.service.SeferService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seferler")
public class SeferController {
    private final SeferService seferService;
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SeferController(SeferService seferService, JdbcTemplate jdbcTemplate) {
        this.seferService = seferService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @CrossOrigin
    @GetMapping
    public List<Seferler> getAllSefer() {
        return seferService.getSefer();
    }

    @PostMapping
    public void createSefer(@RequestBody Seferler sefer) {
        seferService.createSefer(sefer);
    }

    @DeleteMapping(path = "{seferId}")
    public void deleteSefer(@PathVariable("seferId") Long seferId) {
        seferService.deleteSefer(seferId);
    }

    @PutMapping(path = "{seferId}")
    public void updateSefer(@PathVariable("seferId") Long seferId,
                            @RequestBody Seferler sefer) {
        seferService.updateSefer(seferId, sefer);
    }

    @CrossOrigin
    @GetMapping("/nereden")
    public List<Map<String, Object>> getFromCity() {
        String sql = "SELECT Sefer_kalkis_yeri as Sehir FROM Seferler WHERE Sefer_kalkis_yeri IS NOT NULL\n" +
                "GROUP BY Sefer_kalkis_yeri\n";
        return jdbcTemplate.queryForList(sql);
    }

    @CrossOrigin
    @GetMapping("/nereye")
    public List<Map<String, Object>> getToCity() {
        String sql = "SELECT Sefer_varis_yeri as Sehir FROM Seferler WHERE Sefer_varis_yeri IS NOT NULL\n" +
                "GROUP BY Sefer_varis_yeri\n";
        return jdbcTemplate.queryForList(sql);
    }


    @PostMapping("/searchSefer")
    public List<Seferler> getSeferlerByKriter(@RequestBody Map<String, Object> seferKriter) {
        String nereden = (String) seferKriter.get("nereden");
        String nereye = (String) seferKriter.get("nereye");
        LocalDate tarih = LocalDate.parse((String) seferKriter.get("tarih"));

        return seferService.getSeferlerByKriter(nereden, nereye, tarih);
    }
}
