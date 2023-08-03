package com.example.demo.service.impl;

import com.example.demo.model.DangKiHoc;
import com.example.demo.repository.XemDiemRepository;
import com.example.demo.request.XemDiemRequest;
import com.example.demo.response.XemDiemResponse;
import com.example.demo.service.XemDiemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class XemDiemServiceImpl implements XemDiemService {
    private final XemDiemRepository repository;
    @Override
    public ResponseEntity<?> xemDiem(XemDiemRequest request) {
        List<XemDiemResponse> list = repository.findAllBySemesterAndStudentCode(request.getTen(), request.getKiHoc());
        if (Objects.nonNull(list)) {
            return ResponseEntity.ok(list);
        }
        return null;
    }
}
