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
@Table(name = "buoi_hoc")
public class BuoiHoc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ten;
    @Column(name = "mo_ta") private String mota;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lhp_id", referencedColumnName = "id")
    private LopHocPhan lhp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gv_id", referencedColumnName = "id")
    private GiangVien gv;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ph_id", referencedColumnName = "id")
    private PhongHoc ph;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kh_id", referencedColumnName = "id") private KipHoc kh;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nh_id", referencedColumnName = "id")
    private NgayHoc nh;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "th_id", referencedColumnName = "id") private TuanHoc th;

}
