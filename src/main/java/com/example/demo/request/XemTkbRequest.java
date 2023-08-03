package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class XemTkbRequest {
    @JsonProperty("ma_sinh_vien") private String maSinhVien;
    @JsonProperty("ten_ki_hoc") private String tenKiHoc;
}
