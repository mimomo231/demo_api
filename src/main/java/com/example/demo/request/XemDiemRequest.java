package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class XemDiemRequest {
    @NotBlank
    private String ten;
    @NotBlank
    @JsonProperty("ki_hoc")
    private String kiHoc;
}
