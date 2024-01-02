package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.entities.Biletler;
import com.otobusbiletisatissistemi.service.BiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/biletler")
public class BiletController {
    private final BiletService biletService;

    @Autowired
    public BiletController(BiletService biletService) {
        this.biletService = biletService;
    }

    @GetMapping
    public List<Biletler> getAllBilet() {
        return biletService.getBilet();
    }

    @PostMapping
    public void createBilet(@RequestBody Biletler bilet) {
        biletService.createBilet(bilet);
    }

    @DeleteMapping(path = "{biletId}")
    public void deleteBilet(@PathVariable("biletId") Long biletId) {
        biletService.deleteBilet(biletId);
    }

    @PutMapping(path = "{biletId}")
    public void updateBilet(@PathVariable("biletId") Long biletId,
                            @RequestBody Biletler bilet) {
        biletService.updateBilet(biletId, bilet);
    }
}