package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.Dto.SeferDto;
import com.otobusbiletisatissistemi.entities.Firmalar;
import com.otobusbiletisatissistemi.entities.Otobusler;
import com.otobusbiletisatissistemi.entities.Seferler;
import com.otobusbiletisatissistemi.service.SeferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin
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

    @GetMapping
    public List<Seferler> getAllSefer() {
        return seferService.getSefer();
    }

    @PostMapping
    public ResponseEntity<String> createSefer(@RequestBody SeferDto seferDto) {
        Seferler sefer = convertToEntity(seferDto);
        seferService.createSefer(sefer);
        return ResponseEntity.ok("Sefer başarıyla kaydedildi.");
    }
    private Seferler convertToEntity(SeferDto seferDto) {
        Seferler sefer = new Seferler();
        sefer.setSeferKalkisYeri(seferDto.getSeferKalkisYeri());
        sefer.setSeferVarisYeri(seferDto.getSeferVarisYeri());
        sefer.setSeferKalkisSaati((seferDto.getSeferKalkisSaati()));
        sefer.setSeferVarisSaati((seferDto.getSeferVarisSaati()));

        Firmalar firma = new Firmalar();
        firma.setId(seferDto.getFirmaId());
        sefer.setFirma(firma);

        Otobusler otobus = new Otobusler();
        otobus.setId(seferDto.getOtobusId());
        sefer.setOtobusId(seferDto.getOtobusId());

        return sefer;
    }

    @DeleteMapping(path = "{seferId}")
    public void deleteSefer(@PathVariable("seferId") Long seferId) {
        seferService.deleteSefer(seferId);
    }

    @PutMapping(path = "{seferId}")
    public void updateSefer(@PathVariable("seferId") Long seferId,
                            @RequestBody Map<String, Object> seferKriter) {
        Long firmaId = ((Number) seferKriter.get("firmaId")).longValue();
        Long otobusId = ((Number) seferKriter.get("otobusId")).longValue();

        String seferKalkisYeri = (String) seferKriter.get("seferKalkisYeri");
        String seferVarisYeri = (String) seferKriter.get("seferVarisYeri");
        String seferKalkisSaati = (String) seferKriter.get("seferKalkisSaati");
        String seferVarisSaati = (String) seferKriter.get("seferVarisSaati");
        seferService.updateSefer(seferId, firmaId,otobusId,seferKalkisYeri,seferVarisYeri,seferKalkisSaati,seferVarisSaati);
    }

    @GetMapping("/nereden")
    public List<Map<String, Object>> getFromCity() {
        String sql = "SELECT Sefer_kalkis_yeri as Sehir FROM Seferler WHERE Sefer_kalkis_yeri IS NOT NULL\n" +
                "GROUP BY Sefer_kalkis_yeri\n";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/nereye")
    public List<Map<String, Object>> getToCity() {
        String sql = "SELECT Sefer_varis_yeri as Sehir FROM Seferler WHERE Sefer_varis_yeri IS NOT NULL\n" +
                "GROUP BY Sefer_varis_yeri\n";
        return jdbcTemplate.queryForList(sql);
    }


    @PostMapping("/searchSefer")
    public List<Seferler> getSeferlerByKriter(@RequestBody Map<String, Object> seferKriter) {
        String nereden = (String) seferKriter.get("from");
        String nereye = (String) seferKriter.get("to");
        LocalDate tarih = LocalDate.parse((String) seferKriter.get("date"));

        return seferService.getSeferlerByKriter(nereden, nereye, tarih);
    }

    @PostMapping("/getSeferAndBiletFiyat")
    public List<Object> getSeferAndBiletFiyat(@RequestBody Map<String, Object> seferKriter) {
        String nereden = (String) seferKriter.get("from");
        String nereye = (String) seferKriter.get("to");
        LocalDate tarih = LocalDate.parse((String) seferKriter.get("date"));

        return seferService.getSeferAndBiletFiyat(nereden, nereye, tarih);
    }
}
