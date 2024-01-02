package com.otobusbiletisatissistemi.service;

import com.otobusbiletisatissistemi.entities.Firmalar;
import com.otobusbiletisatissistemi.repositories.FirmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FirmaService {
    private final FirmaRepository firmaRepository;

    @Autowired
    public FirmaService(FirmaRepository firmaRepository) {
        this.firmaRepository = firmaRepository;
    }

    public List<Firmalar> getFirma() {
        return firmaRepository.findAll();
    }

    public void createFirma(Firmalar firma) {
        Optional<Firmalar> firmalarOptional = firmaRepository.findFirmalarByFirmaIletisim(firma.getFirmaIletisim());
        if (firmalarOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        firmaRepository.save(firma);
    }

    public void deleteFirma(Long firmaId) {
        boolean exist = firmaRepository.existsById(firmaId);
        if (!exist) {
            throw new IllegalStateException("firma with id " + firmaId + " does not exists");
        }
        firmaRepository.deleteById(firmaId);
    }

    @Transactional
    public void updateFirma(Long firmaId, String firmaAdi, String firmaIletisim, String isletmeAlani) {
        Firmalar firma = firmaRepository.findById(firmaId).orElseThrow(
                () -> new IllegalStateException("firma with id " + "does not exist"));

        if (firmaAdi != null && !firmaAdi.isEmpty() && !Objects.equals(firma.getFirmaAdi(), firmaAdi)) {
            firma.setFirmaAdi(firmaAdi);
        }

        if (firmaIletisim != null && !firmaIletisim.isEmpty() && !Objects.equals(firma.getFirmaIletisim(), firmaIletisim)) {
            Optional<Firmalar> firmalarOptional = firmaRepository.findFirmalarByFirmaIletisim(firmaIletisim);
            if (firmalarOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            firma.setFirmaIletisim(firmaIletisim);
        }

        if (isletmeAlani != null && !isletmeAlani.isEmpty() && !Objects.equals(firma.getIsletmeAlani(), isletmeAlani)) {
            firma.setIsletmeAlani(isletmeAlani);
        }
    }
}
