package com.example.demo.repository;

import com.example.demo.model.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GiangVienRepository extends JpaRepository<GiangVien, Integer> {
    Optional<GiangVien> findByUsername(String username);
}
