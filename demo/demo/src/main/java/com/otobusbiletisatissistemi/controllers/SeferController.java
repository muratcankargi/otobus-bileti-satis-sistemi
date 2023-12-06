package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.entities.Seferler;
import com.otobusbiletisatissistemi.service.SeferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seferler")
public class SeferController {
    private final SeferService seferService;

    @Autowired
    public SeferController(SeferService seferService) {
        this.seferService = seferService;
    }

    @GetMapping
    public List<Seferler> getAllSefer() {
        return seferService.getSefer();
    }

    @PostMapping
    public void createSefer(@RequestBody Seferler sefer) {
        seferService.createSefer(sefer);
    }

    @DeleteMapping(path = "{seferId}")
    public void deleteSefer(@PathVariable("seferId") Long seferId) {
        seferService.deleteSefer(seferId);
    }

    @PutMapping(path = "{seferId}")
    public void updateSefer(@PathVariable("seferId") Long seferId,
                            @RequestBody Seferler sefer) {
        seferService.updateSefer(seferId, sefer);
    }
}
