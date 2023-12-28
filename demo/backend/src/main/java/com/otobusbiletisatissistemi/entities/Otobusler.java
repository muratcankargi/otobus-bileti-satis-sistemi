package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;

@Entity
public class Otobusler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long firmaId;
    private String otobusPlaka;
    private Integer otobusKontenjan;
    private String otobusMarka;
    private Integer modelYili;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getOtobusPlaka() {
        return otobusPlaka;
    }

    public void setOtobusPlaka(String otobusPlaka) {
        this.otobusPlaka = otobusPlaka;
    }

    public Integer getOtobusKontenjan() {
        return otobusKontenjan;
    }

    public void setOtobusKontenjan(Integer otobusKontenjan) {
        this.otobusKontenjan = otobusKontenjan;
    }

    public String getOtobusMarka() {
        return otobusMarka;
    }

    public void setOtobusMarka(String otobusMarka) {
        this.otobusMarka = otobusMarka;
    }

    public Integer getModelYili() {
        return modelYili;
    }

    public void setModelYili(Integer modelYili) {
        this.modelYili = modelYili;
    }

    public Long getFirmaId() {
        return firmaId;
    }

    public void setFirmaId(Long firmaId) {
        this.firmaId = firmaId;
    }
}

