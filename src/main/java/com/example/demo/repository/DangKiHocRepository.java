package com.example.demo.repository;

import com.example.demo.model.DangKiHoc;
import com.example.demo.response.DangKyHocDTO;
import com.example.demo.response.DangKyMonHocResponse;
import com.example.demo.response.XemDiemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DangKiHocRepository extends JpaRepository<DangKiHoc, Integer>, DkhRepoCustom {
}
