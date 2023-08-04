package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter
@AllArgsConstructor
@Builder
public class LopHocPhanDTO {
    @JsonProperty("ma_lop") private Integer maLop;
    @JsonProperty("mo_ta") private String moTa;
    @JsonProperty("si_so_max") private Integer siSoMax;
    @JsonProperty("si_so_real") private Integer siSoReal;
    @JsonProperty("ten_lop") private String tenLop;
    @JsonProperty("ten_mon") private String tenMon;
}
