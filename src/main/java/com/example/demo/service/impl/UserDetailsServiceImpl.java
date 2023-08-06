package com.example.demo.service.impl;

import com.example.demo.common.base.exception.NotFoundException;
import com.example.demo.common.security.model.UserAccountInfo;
import com.example.demo.common.security.model.UserPrincipal;
import com.example.demo.model.GiangVien;
import com.example.demo.model.ThanhVien;
import com.example.demo.repository.GiangVienRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final GiangVienRepository giangVienRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ThanhVien sinhVien = userRepository.findByUsername(username).orElseThrow(
                () ->new NotFoundException(String.format("reset Password error: not found username like that!"))
        );
        if (Objects.nonNull(sinhVien)) {
            return UserPrincipal.build(UserAccountInfo.builder()
                    .userId(Long.valueOf(sinhVien.getId()))
                    .username(sinhVien.getUsername())
                    .password(sinhVien.getPassword())
                    .roles(Collections.singletonList(sinhVien.getRole().name()))
                    .tel(sinhVien.getTel())
                    .studentCode(sinhVien.getMaSinhVien())
                    .build());
        } else {
            GiangVien giangVien = giangVienRepository.findByUsername(username).orElseThrow(
                    () -> new NotFoundException(String.format("reset password error: not found username like that"))
            );
            return UserPrincipal.build(UserAccountInfo.builder()
                    .userId(Long.valueOf(sinhVien.getId()))
                    .username(giangVien.getUsername())
                    .password(giangVien.getPassword())
                    .roles(Collections.singletonList(giangVien.getRole().name()))
                    .tel(giangVien.getTel())
                    .build());
        }
    }
}
