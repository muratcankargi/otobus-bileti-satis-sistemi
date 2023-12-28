package com.otobusbiletisatissistemi.service;

import com.otobusbiletisatissistemi.entities.Firmalar;
import com.otobusbiletisatissistemi.entities.Otobusler;
import com.otobusbiletisatissistemi.repositories.FirmaRepository;
import com.otobusbiletisatissistemi.repositories.OtobusRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OtobusService {
    private final OtobusRepository otobusRepository;
    private final FirmaRepository firmaRepository;

    @Autowired
    public OtobusService(OtobusRepository otobusRepository, FirmaRepository firmaRepository) {
        this.otobusRepository = otobusRepository;
        this.firmaRepository = firmaRepository;
    }


    public List<Otobusler> getOtobus() {
        return otobusRepository.findAll();
    }

    public void createOtobus(Otobusler otobus) {
        Optional<Firmalar> optionalFirmalar = firmaRepository.findById(otobus.getFirmaId());
        if (optionalFirmalar.isEmpty()) {
            throw new IllegalStateException("firma id " + otobus.getFirmaId() + " not found");
        }

        Optional<Otobusler> optionalOtobusler = otobusRepository.findOtobuslerByOtobusPlaka(otobus.getOtobusPlaka());
        if (optionalOtobusler.isPresent()) {
            throw new IllegalStateException("plaka taken");
        }
        otobusRepository.save(otobus);
    }

    public void deleteOtobus(Long otobusId) {
        boolean exist = otobusRepository.existsById(otobusId);
        if (!exist) {
            throw new IllegalStateException("firma with id " + otobusId + " does not exists");
        }

        otobusRepository.deleteById(otobusId);
    }

    @Transactional
    public void updateOtobus(Long otobusId, Otobusler updateOtobus) {
        Otobusler otobus = otobusRepository.findById(otobusId).orElseThrow(
                () -> new IllegalStateException("otobus with id " + otobusId + "does not exist"));

        boolean isPresent = firmaRepository.findById(updateOtobus.getFirmaId()).isPresent();
        if (isPresent) {
            otobus.setFirmaId(otobus.getFirmaId());
        }

        if (updateOtobus.getOtobusPlaka() != null & !updateOtobus.getOtobusPlaka().isEmpty()) {
            Optional<Otobusler> optionalOtobusler = otobusRepository.findOtobuslerByOtobusPlaka(
                    updateOtobus.getOtobusPlaka());
            if (optionalOtobusler.isPresent()) {
                throw new IllegalStateException("plaka taken");
            }
            otobus.setOtobusPlaka(updateOtobus.getOtobusPlaka());
        }

        if (updateOtobus.getOtobusKontenjan() != null) {
            otobus.setOtobusKontenjan(updateOtobus.getOtobusKontenjan());
        }

        if (updateOtobus.getOtobusMarka() != null && !updateOtobus.getOtobusMarka().isEmpty()) {
            otobus.setOtobusMarka(updateOtobus.getOtobusMarka());
        }

        if (updateOtobus.getModelYili() != null) {
            otobus.setModelYili(updateOtobus.getModelYili());
        }
    }

    public List<Otobusler> getOtobusBySeferId(Long seferId) {
        return otobusRepository.getOtobusBySeferId(seferId);
    }
}
