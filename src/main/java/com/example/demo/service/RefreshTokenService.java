package com.example.demo.service;

import com.example.demo.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(Long userId);
    void saveRefreshToken(RefreshToken refreshToken);
    RefreshToken verifyExpiration(RefreshToken token);
}
