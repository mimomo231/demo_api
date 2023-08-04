package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder
public class SinhVienDTO {
    private String username;
    private String msv;
    private String tel;
    private String email;
}
