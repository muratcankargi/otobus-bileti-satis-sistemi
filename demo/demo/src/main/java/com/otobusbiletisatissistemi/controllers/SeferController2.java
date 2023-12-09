package com.otobusbiletisatissistemi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SeferController2 {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //TODO: without entity framework
    @GetMapping("/seferler")
    public List<Map<String, Object>> getAllSeferler() {
        String sql = "SELECT * FROM Seferler";
        return jdbcTemplate.queryForList(sql);
    }
}
