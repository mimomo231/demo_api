package com.example.demo.controller;

import com.example.demo.service.LopHocPhanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/xemlop", produces = "application/json")
@RequiredArgsConstructor
public class LopHocPhanController {
    private final LopHocPhanService service;
    @GetMapping
    ResponseEntity<?> showClassToTeacher(@RequestParam(name = "tid") Integer tid,
                                         @RequestParam(name = "semester") String kten) {
        return service.showClassToTeacher(tid, kten);
    }
}
