package com.example.demo.repository;

import com.example.demo.response.BuoiHocDTO;

import java.util.List;
import java.util.Map;

public interface BuoiHocRepoCustom {
    List<BuoiHocDTO> findTkbByStudentAndSemester(String code, String semester);
}
