package com.example.demo.controller;

import com.example.demo.request.EnterScoreRequest;
import com.example.demo.service.KetQuaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/nhapdiem", produces = "application/json")
public class ScoreController {
    private final KetQuaService service;
    @PostMapping
    ResponseEntity<?> updateScore(@RequestBody @NotBlank EnterScoreRequest request) {
        return service.updateScore(request);
    }
}
