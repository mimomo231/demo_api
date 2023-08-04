package com.example.demo.model;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "phong_hoc")
public class PhongHoc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ten;
    @Column(name = "mo_ta") private String mota;
    @Column(name = "suc_chua") private String succhua;
}
