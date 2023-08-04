package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class UpdateUserRequest {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String tel;
    @JsonProperty("ma_sinh_vien") private String maSinhVien;
}
