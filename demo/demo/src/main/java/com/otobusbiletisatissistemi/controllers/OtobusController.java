package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.entities.Otobusler;
import com.otobusbiletisatissistemi.service.OtobusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/otobusler")
public class OtobusController {
    private final OtobusService otobusService;

    OtobusController(OtobusService otobusService) {
        this.otobusService = otobusService;
    }

    @GetMapping
    public List<Otobusler> gelAllBus() {
        return otobusService.getOtobus();
    }

    @PostMapping
    public void createOtobus(@RequestBody Otobusler otobus) {
        otobusService.createOtobus(otobus);
    }

    @DeleteMapping(path = "{otobusId}")
    public void deleteOtobus(@PathVariable("otobusId") Long otobusId) {
        otobusService.deleteOtobus(otobusId);
    }

    @PutMapping(path = "{otobusId}")
    public void updateOtobus(@PathVariable("otobusId") Long otobusId,
                             @RequestBody Otobusler otobus) {
        otobusService.updateOtobus(otobusId, otobus);
    }
}
