package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.entities.Sefer;
import com.otobusbiletisatissistemi.repositories.SeferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seferler")
public class SeferController {
    @Autowired
    SeferRepository seferRepository;
    @GetMapping
    public List<Sefer> getAllSefer(){
        return seferRepository.findAll();
    }
}
