package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Biletler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Yolcular yolcu;

    @ManyToOne
    private Seferler sefer;

    private Integer KoltukNo;

    private Integer BiletFiyat;

    private Date SatisTarihi;
    public Biletler() {
    }

    public Yolcular getYolcu() {
        return yolcu;
    }

    public void setYolcu(Yolcular yolcu) {
        this.yolcu = yolcu;
    }

    public Seferler getSefer() {
        return sefer;
    }

    public void setSefer(Seferler sefer) {
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
