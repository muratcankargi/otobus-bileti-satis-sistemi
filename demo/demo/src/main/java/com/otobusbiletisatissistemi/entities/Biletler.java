package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table
public class Biletler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long yolcuId;

    private Long seferId;

    private Integer KoltukNo;

    private Integer BiletFiyat;

    private Date SatisTarihi;

    public Biletler() {
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

    public Long getYolcuId() {
        return yolcuId;
    }

    public void setYolcuId(Long yolcuId) {
        this.yolcuId = yolcuId;
    }

    public Long getSeferId() {
        return seferId;
    }

    public void setSeferId(Long seferId) {
        this.seferId = seferId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
