package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "Firmalar")
public class Firma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Firma_adi", nullable = false)
    private String firmaAdi;

    @Column(name = "Firma_iletisim")
    private String firmaIletisim;

    @Column(name = "Isletme_alani")
    private String isletmeAlani;


    public Firma() {

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
