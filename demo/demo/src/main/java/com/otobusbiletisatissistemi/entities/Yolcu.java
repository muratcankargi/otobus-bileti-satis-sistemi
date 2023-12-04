package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Yolcular")
public class Yolcu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String soyad;
    private String TCKimlik_no;
    private String Email;
    private String cinsiyet;

    public Yolcu(String ad, String soyad, String tcKimlikNo, String email, String cinsiyet) {
        this.ad = ad;
        this.soyad = soyad;
        TCKimlik_no = tcKimlikNo;
        Email = email;
        this.cinsiyet = cinsiyet;
    }

    public Yolcu() {

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

    public String getTCKimlik_no() {
        return TCKimlik_no;
    }

    public void setTCKimlik_no(String TCKimlik_no) {
        this.TCKimlik_no = TCKimlik_no;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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
}
