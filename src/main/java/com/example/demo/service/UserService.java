package com.example.demo.service;

import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> login(LoginRequest request);
    ResponseEntity<?> addUser(RegisterRequest request);
}
