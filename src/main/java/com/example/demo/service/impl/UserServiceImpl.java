package com.example.demo.service.impl;

import com.example.demo.common.base.exception.NotFoundException;
import com.example.demo.common.base.exception.TokenRefreshException;
import com.example.demo.common.security.common.JwtTokenCommon;
import com.example.demo.common.security.common.JwtUtils;
import com.example.demo.common.security.model.DemoUserPassAuthenticationToken;
import com.example.demo.common.security.model.TokenRefreshResponse;
import com.example.demo.common.security.model.UserPrincipal;
import com.example.demo.common.security.payload.UserToken;
import com.example.demo.model.GiangVien;
import com.example.demo.model.RefreshToken;
import com.example.demo.model.ThanhVien;
import com.example.demo.model.enumtype.AccountRoleEnum;
import com.example.demo.repository.GiangVienRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.*;
import com.example.demo.response.RegisterResponse;
import com.example.demo.response.SinhVienDTO;
import com.example.demo.service.RefreshTokenService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;
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
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        // Authenticate via authentication manager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        try {
            ThanhVien sinhVien = userRepository.findByUsername(request.getUsername()).orElseThrow(
                    () ->new NotFoundException(String.format("error: not found username like that!"))
            );

            if (Objects.nonNull(sinhVien) && Objects.nonNull(authentication)) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
                UserToken userToken = genTokenInfo(userDetails);

                RefreshToken refreshToken = new RefreshToken();
                refreshToken.setUser(sinhVien);
                refreshToken.setExpiryDate(Instant.now().plusMillis(jwtUtils.getJwtExpirationMs()));
                refreshToken.setToken(userToken.getRefreshToken());

                refreshTokenService.saveRefreshToken(refreshToken);
                return ResponseEntity.ok(userToken);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            GiangVien giangVien = giangVienRepository.findByUsername(request.getUsername()).orElseThrow(
                    () -> new NotFoundException(String.format("error: not found username like that"))
            );

            if (Objects.nonNull(giangVien) && Objects.nonNull(authentication)) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
                return ResponseEntity.ok(genTokenInfo(userDetails));
            }
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
    public ResponseEntity<?> searchUser(String key,int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        List<SinhVienDTO> sinhVienDTOList = userRepository.searchUser(key,page,size,sortBy);
        Map<String, Object> response = new HashMap<>();
        response.put("listSinhVien", sinhVienDTOList);
        response.put("totalItems", sinhVienDTOList.size());
//        response.put("totalPages", sinhVienDTOList.size()/size - 1);
        return ResponseEntity.ok(response);
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
        String rs = null;
        try {
            userRepository.deleteById(uid);
            rs = new String("delete thanh cong");
        } catch (Exception e) {
            rs = new String("delete khong thanh cong");
        }
        return ResponseEntity.ok(rs);
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
            }
        } catch (Exception e) {
            GiangVien giangVien = giangVienRepository.findByUsername(request.getUsername()).orElseThrow(
                    () -> new NotFoundException(String.format("reset password error: not found username like that"))
            );
            if (Objects.nonNull(giangVien)) {
                giangVien.setPassword(passwordEncoder.encode(request.getPassword()));
                giangVienRepository.save(giangVien);
            }
        }
    }

    @Override
    public ResponseEntity<?> refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        try {
            return refreshTokenService.findByToken(requestRefreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        String accessToken = jwtUtils.generateTokenFromUsername(user.getUsername());
                        return ResponseEntity.ok(new TokenRefreshResponse(accessToken, requestRefreshToken));
                    })
                    .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                            "Refresh token is not in database!"));
        } catch (Exception e) {
            return new ResponseEntity<>("not found! please login",HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<?> refreshToken1(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        try {
            return refreshTokenService.findByToken(requestRefreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ThanhVien u = userRepository.findByUsername(user.getUsername()).get();
                        List<String> roles = Arrays.asList(AccountRoleEnum.values()).stream()
                                .filter(accountRoleEnum -> accountRoleEnum == u.getRole())
                                .map(AccountRoleEnum::name)
                                .collect(Collectors.toList());
                        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

                        DemoUserPassAuthenticationToken authenticationToken =
                                new DemoUserPassAuthenticationToken(user.getId().longValue(),
                                        user.getUsername(),null, authorities);

                        String accessToken = jwtTokenCommon.generateTokenFromUsername(user.getUsername(),
                                user.getId().longValue(),authenticationToken.getAuthorities());
                        return ResponseEntity.ok(new TokenRefreshResponse(accessToken, requestRefreshToken));
                    })
                    .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                            "Refresh token is not in database!"));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
