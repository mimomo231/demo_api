package com.example.demo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter @Getter
public class ResetPasswordRequest {
    @NotBlank private String username;
    @NotBlank private String password;
}
