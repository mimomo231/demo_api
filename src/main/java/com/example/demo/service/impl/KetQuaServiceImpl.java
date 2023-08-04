package com.example.demo.service.impl;

import com.example.demo.repository.KetQuaRepository;
import com.example.demo.response.StatisticResponse;
import com.example.demo.service.KetQuaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KetQuaServiceImpl implements KetQuaService {
    private final KetQuaRepository repository;
    @Override
    public ResponseEntity<?> statisticScore() {
        StatisticResponse response = new StatisticResponse(repository.statisticScore());
        return ResponseEntity.ok(response);
    }
}
