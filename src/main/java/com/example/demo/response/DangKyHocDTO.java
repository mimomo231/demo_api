package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter @Setter
@AllArgsConstructor
public class DangKyHocDTO {
    private Integer id;
    @JsonProperty("diem_tb")
    private Float diemTb;
    @JsonProperty("lhp_id")
    private Integer lhpId;
    @JsonProperty("sv_id")
    private Integer svId;
}
