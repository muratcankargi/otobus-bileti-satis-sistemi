package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.entities.Biletler;
import com.otobusbiletisatissistemi.repositories.BiletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/biletler")
public class BiletController {
    @Autowired
    BiletRepository biletRepository;

    @GetMapping
    public List<Biletler> getAllBilet(){
        return biletRepository.findAll();
    }
}
