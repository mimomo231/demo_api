package com.example.demo.common.security.model;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAccountInfo {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private String tel;
    private String studentCode;
    private List<String> roles;
}
