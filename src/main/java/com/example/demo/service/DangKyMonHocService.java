package com.example.demo.service;

import com.example.demo.request.DangKiMonHocRequest;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface DangKyMonHocService {
    ResponseEntity<?> checkDkh(DangKiMonHocRequest request) throws SQLException;
}
