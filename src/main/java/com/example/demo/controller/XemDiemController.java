package com.example.demo.controller;

import com.example.demo.request.XemDiemRequest;
import com.example.demo.response.XemDiemResponse;
import com.example.demo.service.XemDiemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/xemdiem", produces = "application/json")
@RequiredArgsConstructor
public class XemDiemController {
    private final XemDiemService xemDiemService;
    @GetMapping
    ResponseEntity<?> showScore(@RequestBody @Valid
                                              XemDiemRequest request) {

        return xemDiemService.xemDiem(request);
    }
}
