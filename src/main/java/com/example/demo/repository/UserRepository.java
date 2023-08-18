package com.example.demo.repository;

import com.example.demo.model.ThanhVien;
import com.example.demo.request.ResetPasswordRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ThanhVien, Integer>, UserRepoCustom  {
    Optional<ThanhVien> findByUsername(String username);
}
