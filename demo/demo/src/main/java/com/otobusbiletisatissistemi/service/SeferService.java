package com.otobusbiletisatissistemi.service;

import com.otobusbiletisatissistemi.entities.Seferler;
import com.otobusbiletisatissistemi.repositories.FirmaRepository;
import com.otobusbiletisatissistemi.repositories.OtobusRepository;
import com.otobusbiletisatissistemi.repositories.SeferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SeferService {
    private final SeferRepository seferRepository;
    private final OtobusRepository otobusRepository;
    private final FirmaRepository firmaRepository;

    @Autowired
    public SeferService(SeferRepository seferRepository, OtobusRepository otobusRepository, FirmaRepository firmaRepository) {
        this.seferRepository = seferRepository;
        this.otobusRepository = otobusRepository;
        this.firmaRepository = firmaRepository;
    }

    public List<Seferler> getSefer() {
        return seferRepository.findAll();
    }

    public void createSefer(Seferler sefer) {
        boolean otobusExist = otobusRepository.existsById(sefer.getOtobusId());
        if (!otobusExist) {
            throw new IllegalStateException("otobus id " + sefer.getOtobusId() + "not exist");
        }

        boolean firmaExist = firmaRepository.existsById(sefer.getFirmaId());
        if (!firmaExist) {
            throw new IllegalStateException("firma id " + sefer.getFirmaId() + "not exist");
        }

        seferRepository.save(sefer);
    }

    public void deleteSefer(Long seferId) {
        boolean exist = seferRepository.existsById(seferId);
        if (!exist) {
            throw new IllegalStateException("sefer id " + seferId + " does not exist");
        }

        seferRepository.deleteById(seferId);
    }

    @Transactional
    public void updateSefer(Long seferId, Seferler updateSefer) {
        Seferler sefer = seferRepository.findById(seferId).orElseThrow(
                () -> new IllegalStateException("sefer id " + seferId + "does not exist"));

        boolean otobusExist = otobusRepository.existsById(updateSefer.getOtobusId());
        if (!otobusExist) {
            throw new IllegalStateException("otobus id " + updateSefer.getOtobusId() + "not exist");
        } else {
            sefer.setOtobusId(updateSefer.getOtobusId());
        }

        boolean firmaExist = firmaRepository.existsById(updateSefer.getFirmaId());
        if (!firmaExist) {
            throw new IllegalStateException("firma id " + updateSefer.getFirmaId() + "not exist");
        } else {
            sefer.setFirmaId(updateSefer.getFirmaId());
        }

        if (updateSefer.getSeferKalkisYeri() != null && updateSefer.getSeferKalkisYeri().length() > 0 &&
                !Objects.equals(sefer.getSeferKalkisYeri(), updateSefer.getSeferVarisYeri())) {
            sefer.setSeferKalkisYeri(updateSefer.getSeferKalkisYeri());
        }

        if (updateSefer.getSeferVarisYeri() != null && updateSefer.getSeferVarisYeri().length() > 0 &&
                !Objects.equals(sefer.getSeferVarisYeri(), updateSefer.getSeferVarisYeri())) {
            sefer.setSeferVarisYeri(updateSefer.getSeferVarisYeri());
        }

        if (updateSefer.getSeferKalkisSaati() != null &&
                !Objects.equals(sefer.getSeferKalkisSaati(), updateSefer.getSeferKalkisSaati())) {
            sefer.setSeferKalkisSaati(updateSefer.getSeferKalkisSaati());
        }

        if (updateSefer.getSeferVarisSaati() != null &&
                !Objects.equals(sefer.getSeferVarisSaati(), updateSefer.getSeferVarisSaati())) {
            sefer.setSeferVarisSaati(updateSefer.getSeferVarisSaati());
        }

    }
}
