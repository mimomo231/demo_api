package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnterScoreRequest {
    @JsonProperty("dkh_id") private Integer dkhId;
    @JsonProperty("diem_Kt") private Float diemKt;
    @JsonProperty("diem_tp1")private Float diemTp1;
    @JsonProperty("diem_tp2")private Float diemTp2;
    @JsonProperty("diem_thi")private Float diemThi;
}
