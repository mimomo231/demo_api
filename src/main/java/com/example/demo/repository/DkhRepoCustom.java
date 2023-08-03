package com.example.demo.repository;

import com.example.demo.response.DangKyHocDTO;

import java.util.List;
import java.util.Map;

public interface DkhRepoCustom {
    List<DangKyHocDTO> checkDkh(String code, String semester);
    List<Map<String, Object>> test(String code, String semester);
}
