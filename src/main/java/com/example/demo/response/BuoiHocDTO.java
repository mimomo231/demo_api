package com.example.demo.response;

import com.example.demo.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Getter @Setter
@AllArgsConstructor
@Builder
public class BuoiHocDTO {
    private String ten;
    @JsonProperty("lhp_id") private Integer lhpId;
    @JsonProperty("gv_id") private Integer gvId;
    @JsonProperty("ph_id") private Integer phId;
    @JsonProperty("kh_id") private Integer khId;
    @JsonProperty("nh_id") private Integer nhId;
    @JsonProperty("th_id") private Integer thId;
}
