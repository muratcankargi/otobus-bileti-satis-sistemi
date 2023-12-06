package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
public class Seferler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seferNo;

    private String seferKalkisYeri;

    private String seferVarisYeri;
    private LocalDateTime seferKalkisSaati;
    private LocalDateTime seferVarisSaati;
    private Long firmaId;
    private Long otobusId;

    public Seferler() {
    }

    public Long getSeferNo() {
        return seferNo;
    }

    public void setSeferNo(Long id) {
        this.seferNo = id;
    }

    public String getSeferKalkisYeri() {
        return seferKalkisYeri;
    }

    public void setSeferKalkisYeri(String seferKalkisYeri) {
        this.seferKalkisYeri = seferKalkisYeri;
    }

    public String getSeferVarisYeri() {
        return seferVarisYeri;
    }

    public void setSeferVarisYeri(String seferVarisYeri) {
        this.seferVarisYeri = seferVarisYeri;
    }

    public LocalDateTime getSeferKalkisSaati() {
        return seferKalkisSaati;
    }

    public void setSeferKalkisSaati(LocalDateTime seferKalkisSaati) {
        this.seferKalkisSaati = seferKalkisSaati;
    }

    public LocalDateTime getSeferVarisSaati() {
        return seferVarisSaati;
    }

    public void setSeferVarisSaati(LocalDateTime seferVarisSaati) {
        this.seferVarisSaati = seferVarisSaati;
    }

    public Long getFirmaId() {
        return firmaId;
    }

    public void setFirmaId(Long firmaId) {
        this.firmaId = firmaId;
    }

    public Long getOtobusId() {
        return otobusId;
    }

    public void setOtobusId(Long otobusId) {
        this.otobusId = otobusId;
    }
}
