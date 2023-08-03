package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "xem_diem")
public class XemDiemResponse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("diem_tb")
    private Float diemTb;
    @JsonProperty("lhp_id")
    private Integer lhpId;
    @JsonProperty("sv_id")
    private Integer svId;
}
