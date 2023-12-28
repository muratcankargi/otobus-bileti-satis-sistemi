package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Yolcular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ad;
    private String soyad;
    private String tckimlikNo;
    private String email;
    private String cinsiyet;

    public String getTckimlikNo() {
        return tckimlikNo;
    }

    public void setTckimlikNo(String TCKimlikNo) {
        this.tckimlikNo = TCKimlikNo;
    }

    public Yolcular() {

    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }


    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
