package com.otobusbiletisatissistemi.controllers;

import com.otobusbiletisatissistemi.entities.Yolcular;
import com.otobusbiletisatissistemi.service.YolcuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/yolcular")
public class YolcuController {

    private final YolcuService yolcuService;

    @Autowired
    public YolcuController(YolcuService yolcuService) {
        this.yolcuService = yolcuService;
    }

    @GetMapping
    public List<Yolcular> getAllYolcular() {
        return yolcuService.getAllYolcular();
    }

    @PostMapping
    public void createYolcu(@RequestBody Yolcular yolcu) {
        yolcuService.createYolcu(yolcu);
    }

    @DeleteMapping(path = "{yolcuId}")
    public void deleteYolcu(@PathVariable("yolcuId") Long yolcuId) {
        yolcuService.deleteYolcu(yolcuId);
    }

    @PutMapping(path = "{yolcuId}")
    public void updateYolcu(@PathVariable("yolcuId") Long yolcuId,
                            @RequestParam(required = false) String ad,
                            @RequestParam(required = false) String soyad,
                            @RequestParam(required = false) String email) {
        yolcuService.updateYolcu(yolcuId, ad, soyad, email);
    }

    @PostMapping("/getYolcuByEmail")
    public List<Yolcular> getYolcuByEmail(@RequestBody Map<String, Object> seferKriter){
        String email= (String) seferKriter.get("email");
        return yolcuService.getYolcuByEmail(email);
    }
}
