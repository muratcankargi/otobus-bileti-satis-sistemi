package com.otobusbiletisatissistemi.service;

import com.otobusbiletisatissistemi.entities.Biletler;
import com.otobusbiletisatissistemi.repositories.BiletRepository;
import com.otobusbiletisatissistemi.repositories.SeferRepository;
import com.otobusbiletisatissistemi.repositories.YolcuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BiletService {
    private final BiletRepository biletRepository;
    private final YolcuRepository yolcuRepository;
    private final SeferRepository seferRepository;

    @Autowired
    public BiletService(BiletRepository biletRepository, YolcuRepository yolcuRepository,
                        SeferRepository seferRepository) {
        this.biletRepository = biletRepository;
        this.yolcuRepository = yolcuRepository;
        this.seferRepository = seferRepository;
    }

    public List<Biletler> getBilet() {
        return biletRepository.findAll();
    }

    public void createBilet(Biletler bilet) {
        boolean existYolcu = yolcuRepository.existsById(bilet.getYolcuId());
        if (!existYolcu) {
            throw new IllegalStateException("no yolcu with ID number " + bilet.getYolcuId());
        }

        boolean existSefer = seferRepository.existsById(bilet.getSeferId());
        if (!existSefer) {
            throw new IllegalStateException("no sefer with ID number " + bilet.getSeferId());
        }

        if (biletRepository.existsBySeferIdAndKoltukNo(bilet.getSeferId(), bilet.getKoltukNo())) {
            throw new IllegalStateException("This seat number is already taken.");
        }

        biletRepository.save(bilet);

    }

    public void deleteBilet(Long biletId) {
        biletRepository.findById(biletId).orElseThrow(
                () -> new IllegalStateException("with bilet is " + biletId + " does not exist"));

        biletRepository.deleteById(biletId);
    }

    @Transactional
    public void updateBilet(Long biletId, Biletler updateBilet) {
        Biletler bilet = biletRepository.findById(biletId).orElseThrow(
                () -> new IllegalStateException("with bilet is " + biletId + " does not exist"));

        boolean existYolcu = yolcuRepository.existsById(updateBilet.getYolcuId());
        if (existYolcu) {
            bilet.setYolcuId(updateBilet.getYolcuId());
        } else {
            throw new IllegalStateException("no yolcu with ID number " + updateBilet.getYolcuId());
        }

        boolean existSefer = seferRepository.existsById(updateBilet.getSeferId());

        if (updateBilet.getKoltukNo() != null && updateBilet.getSeferId() != null) {
            if (biletRepository.existsBySeferIdAndKoltukNo(updateBilet.getSeferId(), updateBilet.getKoltukNo())) {
                throw new IllegalStateException("This seat number is already taken.");
            }
            if (!Objects.equals(updateBilet.getKoltukNo(), bilet.getKoltukNo())) {
                bilet.setKoltukNo(updateBilet.getKoltukNo());
            }
            if (!Objects.equals(updateBilet.getSeferId(), bilet.getSeferId()) && existSefer) {
                bilet.setSeferId(updateBilet.getSeferId());
            }
        }

        if (updateBilet.getBiletFiyat() != null &&
                !Objects.equals(bilet.getBiletFiyat(), updateBilet.getBiletFiyat())) {
            bilet.setBiletFiyat(updateBilet.getBiletFiyat());
        }

        if (updateBilet.getSatisTarihi() != null &&
                !Objects.equals(bilet.getSatisTarihi(), updateBilet.getSatisTarihi())) {
            bilet.setSatisTarihi(updateBilet.getSatisTarihi());
        }

    }

    /*public List<Integer> getBiletFiyat(Long seferId) {
        return biletRepository.getBiletFiyat(seferId);
    }*/
}
