package com.example.demo.repository;

import com.example.demo.model.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<ThanhVien, Integer> {
    Optional<ThanhVien> findByUsername(String username);
}
