package com.example.demo.repository;

import com.example.demo.response.BuoiHocDTO;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
public interface BuoiHocRepoCustom {
    List<BuoiHocDTO> findTkbByStudentAndSemester(String code, String semester);
}
