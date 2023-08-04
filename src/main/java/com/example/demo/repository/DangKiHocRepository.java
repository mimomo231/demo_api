package com.example.demo.repository;

import com.example.demo.model.DangKiHoc;
import com.example.demo.repository.support.HieuJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DangKiHocRepository extends JpaRepository<DangKiHoc, Integer>, DkhRepoCustom {
}
