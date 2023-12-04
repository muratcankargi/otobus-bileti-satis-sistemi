package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Biletler")
public class Bilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "Yolcu_id",nullable = false)
    @ManyToOne
    private Yolcu yolcu;

    @JoinColumn(name = "Sefer_id", nullable = false)
    @ManyToOne
    private Sefer sefer;

    @JoinColumn(name = "Koltuk_no", nullable = false)
    private Integer KoltukNo;

    @JoinColumn(name = "Bilet_fiyat", nullable = false)
    private Integer BiletFiyat;

    @JoinColumn(name = "Satis_tarihi", nullable = false)
    private Date SatisTarihi;
    public Bilet() {
    }

    public Yolcu getYolcu() {
        return yolcu;
    }

    public void setYolcu(Yolcu yolcu) {
        this.yolcu = yolcu;
    }

    public Sefer getSefer() {
        return sefer;
    }

    public void setSefer(Sefer sefer) {
        this.sefer = sefer;
    }

    public Integer getKoltukNo() {
        return KoltukNo;
    }

    public void setKoltukNo(Integer koltukNo) {
        KoltukNo = koltukNo;
    }

    public Integer getBiletFiyat() {
        return BiletFiyat;
    }

    public void setBiletFiyat(Integer biletFiyat) {
        BiletFiyat = biletFiyat;
    }

    public Date getSatisTarihi() {
        return SatisTarihi;
    }

    public void setSatisTarihi(Date satisTarihi) {
        SatisTarihi = satisTarihi;
    }
}
