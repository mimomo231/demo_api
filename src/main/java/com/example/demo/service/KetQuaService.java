package com.example.demo.service;

import com.example.demo.request.EnterScoreRequest;
import org.springframework.http.ResponseEntity;

public interface KetQuaService {
    ResponseEntity<?> statisticScore();
    ResponseEntity<?> updateScore(EnterScoreRequest request);
}
