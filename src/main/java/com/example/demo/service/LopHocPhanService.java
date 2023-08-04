package com.example.demo.service;

import org.springframework.http.ResponseEntity;

public interface LopHocPhanService {
    ResponseEntity<?> showClassToTeacher(Integer tid, String semester);
}
