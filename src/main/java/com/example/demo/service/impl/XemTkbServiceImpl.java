package com.example.demo.service.impl;

import com.example.demo.repository.BuoiHocRepository;
import com.example.demo.repository.DangKiHocRepository;
import com.example.demo.request.XemTkbRequest;
import com.example.demo.response.BuoiHocDTO;
import com.example.demo.service.XemTkbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class XemTkbServiceImpl implements XemTkbService {
    private final BuoiHocRepository repository;
    @Override
    public ResponseEntity<?> xemTkb(XemTkbRequest request) {
        List<BuoiHocDTO> list = repository.findTkbByStudentAndSemester(request.getMaSinhVien(), request.getTenKiHoc());
        return ResponseEntity.ok(list);
    }
}
