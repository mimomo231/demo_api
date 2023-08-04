package com.example.demo.controller;

import com.example.demo.service.KetQuaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/statistic", produces = "application/json")
public class StatisticController {
    private final KetQuaService service;
    @GetMapping
    ResponseEntity<?> showStatistic() {
        return service.statisticScore();
    }
}
