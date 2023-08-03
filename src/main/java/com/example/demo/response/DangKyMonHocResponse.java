package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DangKyMonHocResponse {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("diem_tb")
    Float diemTb;
    @JsonProperty("lhp_id")
    Integer lhpId;
    @JsonProperty("sv_id")
    Integer svId;
}
