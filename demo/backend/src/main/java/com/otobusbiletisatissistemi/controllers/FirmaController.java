package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.entities.Firmalar;
import com.otobusbiletisatissistemi.service.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/firmalar")
public class FirmaController {
    private final FirmaService firmaService;

    @Autowired
    public FirmaController(FirmaService firmaService) {
        this.firmaService = firmaService;
    }

    @GetMapping
    public List<Firmalar> getAllFirmalar() {
        return firmaService.getFirma();
    }

    @PostMapping
    public void createFirma(@RequestBody Firmalar firma) {
        firmaService.createFirma(firma);
    }

    @DeleteMapping(path = "{firmaId}")
    public void deleteFirma(@PathVariable("firmaId") Long firmaId) {
        firmaService.deleteFirma(firmaId);
    }

    @PutMapping(path = "{firmaId}")
    public void updateFirma(@PathVariable("firmaId") Long firmaId,
                            @RequestParam(required = false) String firmaAdi,
                            @RequestParam(required = false) String firmaIletisim,
                            @RequestParam(required = false) String isletmeAlani) {
        firmaService.updateFirma(firmaId, firmaAdi, firmaIletisim, isletmeAlani);
    }
}
