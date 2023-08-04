package com.example.demo.service.impl;

import com.example.demo.repository.LopHocPhanRepository;
import com.example.demo.service.LopHocPhanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LopHocPhanServiceImpl implements LopHocPhanService {
    private final LopHocPhanRepository repository;
    @Override
    public ResponseEntity<?> showClassToTeacher(Integer tid, String kten) {
        return ResponseEntity.ok(
                repository.findAllClassByTeacher(tid, kten)
        );
    }
}
