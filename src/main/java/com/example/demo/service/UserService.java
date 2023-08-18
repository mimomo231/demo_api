package com.example.demo.service;

import com.example.demo.common.security.model.UserPrincipal;
import com.example.demo.request.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> login(LoginRequest request);
    ResponseEntity<?> addUser(AddUserRequest request);
    ResponseEntity<?> searchUser(String key, int page, int size, String sortBy);
    ResponseEntity<?> updateUser(UpdateUserRequest request);
    ResponseEntity<?> deleteUser(Integer request);
    ResponseEntity<?> getListUserInClass(Integer id);
    void resetPassword(ResetPasswordRequest request);
    ResponseEntity<?> refreshToken(TokenRefreshRequest request);
    ResponseEntity<?> refreshToken1(TokenRefreshRequest request);
}
