package com.example.demo.service;

import com.example.demo.request.*;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> login(LoginRequest request);
    ResponseEntity<?> addUser(AddUserRequest request);
    ResponseEntity<?> searchUser(String request);
    ResponseEntity<?> updateUser(UpdateUserRequest request);
    ResponseEntity<?> deleteUser(Integer request);
    ResponseEntity<?> getListUserInClass(Integer id);
}
