package com.otobusbiletisatissistemi.Dto;

import java.time.LocalDateTime;

// SeferDto.java
public class SeferDto {
    private Long firmaId;
    private Long otobusId;
    private String seferKalkisYeri;
    private String seferVarisYeri;
    private LocalDateTime seferKalkisSaati;
    private LocalDateTime seferVarisSaati;

    public SeferDto() {
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
}
