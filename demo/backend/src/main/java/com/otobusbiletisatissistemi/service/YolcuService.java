package com.otobusbiletisatissistemi.service;

import com.otobusbiletisatissistemi.entities.Yolcular;
import com.otobusbiletisatissistemi.repositories.YolcuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class YolcuService {
    private final YolcuRepository yolcuRepository;

    @Autowired
    public YolcuService(YolcuRepository yolcuRepository) {
        this.yolcuRepository = yolcuRepository;
    }

    public List<Yolcular> getAllYolcular() {
        return yolcuRepository.findAll();
    }

    public void createYolcu(Yolcular yolcu) {
        Optional<Yolcular> studentOptional = yolcuRepository.findYolcularByEmail(yolcu.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        yolcuRepository.createYolcu(yolcu.getAd(), yolcu.getSoyad(), yolcu.getTckimlikNo(), yolcu.getEmail(),
                yolcu.getCinsiyet());
    }

    public void deleteYolcu(Long yolcuId) {
        boolean exist = yolcuRepository.existsById(yolcuId);
        if (!exist) {
            throw new IllegalStateException("yolcu with id " + yolcuId + " does not exists");
        }
        yolcuRepository.deleteById(yolcuId);
    }

    @Transactional
    public void updateYolcu(Long yolcuId, String ad, String soyad, String email) {
        Yolcular yolcu = yolcuRepository.findById(yolcuId).orElseThrow(
                () -> new IllegalStateException("student with id " + yolcuId + "does not exist"));

        if (ad != null && !ad.isEmpty() && !Objects.equals(yolcu.getAd(), ad)) {
            yolcu.setAd(ad);
        }

        if (soyad != null && !soyad.isEmpty() && !Objects.equals(yolcu.getSoyad(), soyad)) {
            yolcu.setSoyad(soyad);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(yolcu.getEmail(), email)) {
            Optional<Yolcular> yolcuOptional = yolcuRepository.findYolcularByEmail(email);

            if (yolcuOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            yolcu.setEmail(email);
        }

    }

    public List<Yolcular> getYolcuByEmail(String email) {
        return yolcuRepository.getYolcuByEmail(email);
    }
}
