package com.otobusbiletisatissistemi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Otobusler")
public class Otobus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Firma_id", nullable = false)
    private Firma firma;

    @Column(name = "Otobus_plaka", nullable = false)
    private String otobusPlaka;

    @Column(name = "Otobus_kontenjan", nullable = false)
    private Integer otobusKontenjan;

    @Column(name = "Otobus_marka")
    private String otobusMarka;

    @Column(name = "Model_yili")
    private Integer modelYili;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
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
}

