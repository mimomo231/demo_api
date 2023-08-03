package com.example.demo.service;

import com.example.demo.request.XemDiemRequest;
import org.springframework.http.ResponseEntity;

public interface XemDiemService {
    ResponseEntity<?> xemDiem(XemDiemRequest request);
}
