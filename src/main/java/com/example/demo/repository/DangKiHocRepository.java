package com.example.demo.repository;

import com.example.demo.model.DangKiHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DangKiHocRepository extends JpaRepository<DangKiHoc, Integer>, DkhRepoCustom {
}
