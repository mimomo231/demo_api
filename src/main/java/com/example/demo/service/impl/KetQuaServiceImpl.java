package com.example.demo.service.impl;

import com.example.demo.model.KetQua;
import com.example.demo.repository.KetQuaRepository;
import com.example.demo.request.EnterScoreRequest;
import com.example.demo.response.StatisticResponse;
import com.example.demo.service.KetQuaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KetQuaServiceImpl implements KetQuaService {
    private final KetQuaRepository repository;
    @Override
    public ResponseEntity<?> statisticScore() {
        StatisticResponse response = new StatisticResponse(repository.statisticScore());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> updateScore(EnterScoreRequest request) {
        List<KetQua> list = repository.findAllByDkhId(request.getDkhId());
        for(int i = 0; i < 4; i++) {
            list.get(i).setDiem(request.getDiemKt());
        }
        repository.saveAll(list);
        return ResponseEntity.ok("Nhap diem thanh cong");
    }
}
