package com.example.demo.repository;


import com.example.demo.response.LopHocPhanDTO;

import java.util.List;

public interface LopHocPhanRepoCustom {
    List<LopHocPhanDTO> findAllClassByTeacher(Integer tid, String semester);
}
