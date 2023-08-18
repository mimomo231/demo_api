package com.example.demo.common.base.exception;

import org.springframework.http.HttpStatus;

public class TokenRefreshException extends BaseApiException{
    private static final long serialVersionUID = 1L;

    public TokenRefreshException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
        super.setCode(HttpStatus.FORBIDDEN.value());
    }
}
