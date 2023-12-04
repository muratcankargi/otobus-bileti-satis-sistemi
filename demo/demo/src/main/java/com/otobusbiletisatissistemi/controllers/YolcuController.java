package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.entities.Yolcu;
import com.otobusbiletisatissistemi.repositories.YolcuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/yolcular")
public class YolcuController {

    @Autowired
    private YolcuRepository yolcuRepository;

    @GetMapping
    public List<Yolcu> getAllYolcular() {
        return yolcuRepository.findAll();
    }
}
