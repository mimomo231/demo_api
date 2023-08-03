package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter @Setter
public class RegisterRequest {
    @NotBlank
    @JsonProperty("username")
    private String username;

    @NotBlank
    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("tel")
    private String tel;
    @NotBlank
    @JsonProperty("dtype")
    private String dtype;
    @NotBlank
    @JsonProperty("ma_sinh_vien")
    private String msv;
}
