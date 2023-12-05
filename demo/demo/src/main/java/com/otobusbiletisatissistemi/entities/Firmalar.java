package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;


@Entity
public class Firmalar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firmaAdi;

    private String firmaIletisim;

    private String isletmeAlani;


    public Firmalar() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirmaAdi() {
        return firmaAdi;
    }

    public void setFirmaAdi(String firmaAdi) {
        this.firmaAdi = firmaAdi;
    }

    public String getFirmaIletisim() {
        return firmaIletisim;
    }

    public void setFirmaIletisim(String firmaIletisim) {
        this.firmaIletisim = firmaIletisim;
    }

    public String getIsletmeAlani() {
        return isletmeAlani;
    }

    public void setIsletmeAlani(String isletmeAlani) {
        this.isletmeAlani = isletmeAlani;
    }
}
