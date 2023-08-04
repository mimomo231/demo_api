package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DangKiMonHocRequest {
    @JsonProperty("ma_sinh_vien") private String maSinhVien;
    @JsonProperty("lhp_id") private Integer lhpId;
    @JsonProperty("ten_ki_hoc") private String ten;
    @JsonProperty("mo_ta") private String moTa;
    @JsonProperty("si_so_max") private Integer siSoMax;
    @JsonProperty("si_so_real") private Integer siSoReal;
    @JsonProperty("ten_lop") private String tenLop;
    @JsonProperty("mhkh_id") private Integer mhkhId;
}
