package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.entities.Otobusler;
import com.otobusbiletisatissistemi.repositories.OtobusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/otobusler")
public class OtobusController {
    @Autowired
    OtobusRepository otobusRepository;

    @GetMapping
    public List<Otobusler> gelAllBus(){
        return otobusRepository.findAll();
    }
}
