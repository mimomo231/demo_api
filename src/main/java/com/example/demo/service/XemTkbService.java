package com.example.demo.service;

import com.example.demo.request.XemTkbRequest;
import org.springframework.http.ResponseEntity;

public interface XemTkbService {
    ResponseEntity<?> xemTkb(XemTkbRequest request);
}
