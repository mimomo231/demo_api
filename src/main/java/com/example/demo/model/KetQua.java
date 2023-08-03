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
@Table(name = "ket_qua")
public class KetQua implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float diem;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_id", referencedColumnName = "id")
    private DauDiem diemTp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dkh_id", referencedColumnName = "id") private DangKiHoc dangKiHoc;
}
