package com.example.demo.controller;

import com.example.demo.request.XemTkbRequest;
import com.example.demo.service.XemTkbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/tkb", produces = "application/json")
public class XemThoiKhoaBieuController {
    private final XemTkbService service;
    @GetMapping
    ResponseEntity<?> xemTkb(@RequestBody @Valid
                             XemTkbRequest request) {
        return service.xemTkb(request);
    }
}
