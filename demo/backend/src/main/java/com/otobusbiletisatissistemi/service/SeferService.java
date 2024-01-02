package com.otobusbiletisatissistemi.service;

import com.otobusbiletisatissistemi.entities.Biletler;
import com.otobusbiletisatissistemi.entities.Firmalar;
import com.otobusbiletisatissistemi.entities.Seferler;
import com.otobusbiletisatissistemi.repositories.BiletRepository;
import com.otobusbiletisatissistemi.repositories.FirmaRepository;
import com.otobusbiletisatissistemi.repositories.OtobusRepository;
import com.otobusbiletisatissistemi.repositories.SeferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.*;
import java.util.*;

@Service
public class SeferService {
    private final SeferRepository seferRepository;
    private final OtobusRepository otobusRepository;
    private final FirmaRepository firmaRepository;
    private final BiletRepository biletRepository;

    @Autowired
    public SeferService(SeferRepository seferRepository, OtobusRepository otobusRepository, FirmaRepository firmaRepository, BiletRepository biletRepository) {
        this.seferRepository = seferRepository;
        this.otobusRepository = otobusRepository;
        this.firmaRepository = firmaRepository;
        this.biletRepository = biletRepository;
    }

    public List<Seferler> getSefer() {
        return seferRepository.findAll();
    }

    public void createSefer(Seferler sefer) {
        boolean otobusExist = otobusRepository.existsById(sefer.getOtobusId());
        if (!otobusExist) {
            throw new IllegalStateException("otobus id " + sefer.getOtobusId() + "not exist");
        }

        /*Optional<Firmalar> firma = firmaRepository.findById(sefer.());
        if (firma.isEmpty()) {
            throw new IllegalStateException("no firma");
        }*/
        Seferler savedSefer = seferRepository.save(sefer);
        createBilet(savedSefer.getSeferNo());
    }

    public void createBilet(Long seferId) {
        Biletler bilet = new Biletler();
        bilet.setSeferId(seferId);
        bilet.setSatisTarihi(Date.valueOf(LocalDate.now()));
        bilet.setBiletFiyat((int) (Math.random() * 20) * 10 + 100); //100 - 300 arası sayı üretir.
        bilet.setYolcuId(1L);
        bilet.setKoltukNo(0);
        biletRepository.save(bilet);
    }

    public void deleteSefer(Long seferId) {
        boolean exist = seferRepository.existsById(seferId);
        if (!exist) {
            throw new IllegalStateException("sefer id " + seferId + " does not exist");
        }

        seferRepository.deleteById(seferId);
    }

    @Transactional
    public void updateSefer(Long seferId, Long firmaId, Long otobusId, String seferKalkisYeri, String seferVarisYeri,
                            String seferKalkisSaati, String seferVarisSaati) {
        Seferler sefer = seferRepository.findById(seferId).orElseThrow(
                () -> new IllegalStateException("sefer id " + seferId + "does not exist"));

        boolean otobusExist = otobusRepository.existsById(otobusId);
        if (!otobusExist) {
            throw new IllegalStateException("otobus id " + otobusId + "not exist");
        } else {
            sefer.setOtobusId(otobusId);
        }

        boolean firmaExist = firmaRepository.existsById(firmaId);
        if (!firmaExist) {
            throw new IllegalStateException("firma id " + firmaId + "not exist");
        } else {
            System.out.println(firmaId);
            Firmalar firma = firmaRepository.findById(firmaId).get();
            sefer.setFirma(firma);
        }

        if (seferKalkisYeri != null && !seferKalkisYeri.isEmpty() &&
                !Objects.equals(sefer.getSeferKalkisYeri(), seferKalkisYeri)) {
            sefer.setSeferKalkisYeri(seferKalkisYeri);
        }

        if (seferVarisYeri != null && !seferVarisYeri.isEmpty() &&
                !Objects.equals(sefer.getSeferVarisYeri(), seferVarisYeri)) {
            sefer.setSeferVarisYeri(seferVarisYeri);
        }

        if (seferKalkisSaati != null &&
                !Objects.equals(sefer.getSeferKalkisSaati(), seferKalkisSaati)) {
            sefer.setSeferKalkisSaati(LocalDateTime.parse(seferKalkisSaati));
        }

        if (seferVarisSaati != null &&
                !Objects.equals(sefer.getSeferVarisSaati(), seferVarisSaati)) {
            sefer.setSeferVarisSaati(LocalDateTime.parse(seferVarisSaati));
        }

    }

    public List<Seferler> getSeferlerByKriter(String nereden, String nereye, LocalDate tarih) {
        LocalDateTime startOfDay = LocalDateTime.of(tarih, LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(tarih, LocalTime.MAX);
        return seferRepository.getSeferlerByKriter(nereden, nereye, startOfDay, endOfDay);
    }


    public List<Object> getSeferAndBiletFiyat(String nereden, String nereye, LocalDate tarih) {
        LocalDateTime startOfDay = LocalDateTime.of(tarih, LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(tarih, LocalTime.MAX);
        return seferRepository.getSeferAndBiletFiyat(nereden, nereye, startOfDay, endOfDay);

    }


}
