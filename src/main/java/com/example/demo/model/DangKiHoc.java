package com.example.demo.model;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "dang_ki_hoc")
public class DangKiHoc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sv_id", referencedColumnName = "id") private ThanhVien sv;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lhp_id", referencedColumnName = "id")
    private LopHocPhan lhp;
    @Column(name = "ds_diem_tp")
    @OneToMany(mappedBy = "dangKiHoc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<KetQua> dsDiemTp;
    @Column(name = "diem_tb") private Float diemTb;
}
