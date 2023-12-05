package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Seferler")
public class Sefer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "Sefer_no", nullable = false)
    private Long Sefer_no;

    @JoinColumn(name = "Sefer_kalkis_yeri",nullable = false)
    private String Sefer_kalkis_yeri;

    @JoinColumn(name = "Sefer_varis_yeri",nullable = false)
    private String Sefer_varis_yeri;

    @JoinColumn(name = "Sefer_kalkis_saati", nullable = false)
    private Date Sefer_kalkis_saati;

    @JoinColumn(name = "Sefer_varis_saati", nullable = false)
    private Date Sefer_varis_saati;

    @JoinColumn(name = "Firma_id", nullable = false)
    @ManyToOne
    private Firma Firma_id;

    @JoinColumn(name = "Otobus_id", nullable = false)
    @ManyToOne
    private Otobus Otobus_id;

    public Long getSefer_no() {
        return Sefer_no;
    }

    public Sefer() {
    }

    public void setSefer_no(Long id) {
        this.Sefer_no = id;
    }

    public String getSefer_kalkis_yeri() {
        return Sefer_kalkis_yeri;
    }

    public void setSefer_kalkis_yeri(String sefer_kalkis_yeri) {
        Sefer_kalkis_yeri = sefer_kalkis_yeri;
    }

    public String getSefer_varis_yeri() {
        return Sefer_varis_yeri;
    }

    public void setSefer_varis_yeri(String sefer_varis_yeri) {
        Sefer_varis_yeri = sefer_varis_yeri;
    }

    public Date getSefer_kalkis_saati() {
        return Sefer_kalkis_saati;
    }

    public void setSefer_kalkis_saati(Date sefer_kalkis_saati) {
        Sefer_kalkis_saati = sefer_kalkis_saati;
    }

    public Date getSefer_varis_saati() {
        return Sefer_varis_saati;
    }

    public void setSefer_varis_saati(Date sefer_varis_saati) {
        Sefer_varis_saati = sefer_varis_saati;
    }

    public Firma getFirma_id() {
        return Firma_id;
    }

    public void setFirma_id(Firma firma_id) {
        this.Firma_id = firma_id;
    }

    public Otobus getOtobus_id() {
        return Otobus_id;
    }

    public void setOtobus_id(Otobus otobus_id) {
        this.Otobus_id = otobus_id;
    }
}
