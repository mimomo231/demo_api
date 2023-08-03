package com.example.demo.service.impl;

import com.example.demo.model.GiangVien;
import com.example.demo.model.SinhVien;
import com.example.demo.model.ThanhVien;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegisterRequest;
import com.example.demo.response.RegisterResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        try{
            SinhVien user = (SinhVien) userRepository.findByUsername(request.getUsername()).get();
            if(Objects.nonNull(user)) {
                return ResponseEntity.ok(user);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> addUser(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        try{
            ThanhVien user = userRepository.findByUsername(request.getUsername()).get();
            if(Objects.nonNull(user)) {
                response.setMessage("Tai khoan da ton tai");
            } else {
                response.setMessage("Tao tai khoan thanh cong vui long dang nhap!!");
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
