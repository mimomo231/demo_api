package com.example.demo.service.impl;

import com.example.demo.model.ThanhVien;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.*;
import com.example.demo.response.RegisterResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        try{
            ThanhVien user = userRepository.findByUsername(request.getUsername()).get();
            if(Objects.nonNull(user)) {
                return ResponseEntity.ok(user);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> addUser(AddUserRequest request) {
        RegisterResponse response = new RegisterResponse();
        try{
            ThanhVien user = userRepository.findByUsername(request.getUsername()).get();
            if(Objects.nonNull(user)) {
                response.setMessage("Tai khoan da ton tai");
            }
        }catch (Exception ex) {
            ThanhVien t = ThanhVien.builder()
                    .tel(request.getTel())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .username(request.getUsername())
                    .maSinhVien(request.getMsv()).build();
            userRepository.save(t);
            response.setMessage("add thanh cong");
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> searchUser(String request) {
        List<ThanhVien> thanhVienList = userRepository.findAll();
        return ResponseEntity.ok(thanhVienList);
    }

    @Override
    public ResponseEntity<?> updateUser(UpdateUserRequest request) {
        ThanhVien t = ThanhVien.builder()
                .id(request.getId())
                .email(request.getEmail())
                .tel(request.getTel()).build();
        try {
            userRepository.save(t);
        } catch (Exception e) {
            return ResponseEntity.ok("update that bai");
        }
        return ResponseEntity.ok("Update thanh cong");
    }

    @Override
    public ResponseEntity<?> deleteUser(Integer uid) {
        try {
            userRepository.deleteById(uid);
        } catch (Exception e) {
            return ResponseEntity.ok("Delete khong thanh cong");
        }
        return ResponseEntity.ok("Delete thanh cong");
    }

    @Override
    public ResponseEntity<?> getListUserInClass(Integer lid) {
//        return null;
        return ResponseEntity.ok(userRepository.findAllUserByClass(lid));
    }
}
