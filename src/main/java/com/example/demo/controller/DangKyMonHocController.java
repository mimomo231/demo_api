package com.example.demo.controller;

import com.example.demo.request.DangKiMonHocRequest;
import com.example.demo.service.DangKyMonHocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.sql.SQLException;

@Slf4j
@RestController
@RequestMapping(path = "/api/dangkimonhoc", produces = "application/json")
@RequiredArgsConstructor
public class DangKyMonHocController {
    private final DangKyMonHocService dangKyMonHocService;

    @GetMapping("/test")
    String hello(){
        return "hello";
    }
    @PostMapping()
    ResponseEntity<?> checkDkh(@RequestBody @NotBlank DangKiMonHocRequest request) throws SQLException {
        return dangKyMonHocService.checkDkh(request);
    }
}
