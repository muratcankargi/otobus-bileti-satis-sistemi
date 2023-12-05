package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.entities.Firmalar;
import com.otobusbiletisatissistemi.repositories.FirmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/firmalar")
public class FirmaController {
    @Autowired
    FirmaRepository firmaRepository;

    @GetMapping
    public List<Firmalar> getAllFirmalar(){
        return firmaRepository.findAll();
    }
}
