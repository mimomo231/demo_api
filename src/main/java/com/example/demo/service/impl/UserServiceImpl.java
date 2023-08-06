package com.example.demo.service.impl;

import com.example.demo.common.base.exception.NotFoundException;
import com.example.demo.common.security.common.JwtTokenCommon;
import com.example.demo.common.security.model.UserPrincipal;
import com.example.demo.common.security.payload.UserToken;
import com.example.demo.model.GiangVien;
import com.example.demo.model.ThanhVien;
import com.example.demo.repository.GiangVienRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.*;
import com.example.demo.response.RegisterResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GiangVienRepository giangVienRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenCommon jwtTokenCommon;
    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        // Authenticate via authentication manager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        try{
            ThanhVien sinhVien = userRepository.findByUsername(request.getUsername()).orElseThrow(
                    () ->new NotFoundException(String.format("error: not found username like that!"))
            );

            if (Objects.nonNull(sinhVien) && Objects.nonNull(authentication)) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
                return ResponseEntity.ok(genTokenInfo(userDetails));
            } else {
                GiangVien giangVien = giangVienRepository.findByUsername(request.getUsername()).orElseThrow(
                        () -> new NotFoundException(String.format("error: not found username like that"))
                );

                if (Objects.nonNull(giangVien) && Objects.nonNull(authentication)) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
                    return ResponseEntity.ok(genTokenInfo(userDetails));
                }
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    private UserToken genTokenInfo(UserPrincipal principal) {

        String accessToken = jwtTokenCommon.generateJwtToken(principal);
        String refreshToken = jwtTokenCommon.generateJwtRefreshToken(principal);

        Set<String> roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return UserToken.builder()
                .accountId(principal.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .listRole(roles)
                .username(principal.getUsername())
                .email(principal.getEmail())
                .studentCode(principal.getStudentCode())
                .build();
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

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        try {
            ThanhVien sinhVien = userRepository.findByUsername(request.getUsername()).orElseThrow(
                    () ->new NotFoundException(String.format("reset Password error: not found username like that!"))
            );
            if (Objects.nonNull(sinhVien)) {
                sinhVien.setPassword(passwordEncoder.encode(request.getPassword()));
                userRepository.save(sinhVien);
            } else {
                GiangVien giangVien = giangVienRepository.findByUsername(request.getUsername()).orElseThrow(
                        () -> new NotFoundException(String.format("reset password error: not found username like that"))
                );
                if (Objects.nonNull(giangVien)) {
                    giangVien.setPassword(passwordEncoder.encode(request.getPassword()));
                    giangVienRepository.save(giangVien);
                }
            }
        }catch (NotFoundException e) {
        }
    }
}
