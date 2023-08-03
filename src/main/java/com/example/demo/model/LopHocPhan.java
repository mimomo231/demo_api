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
@Table(name = "lop_hoc_phan")
public class LopHocPhan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ten;
    @Column(name = "si_so_max")private Integer siSoMax;
    @Column(name = "si_so_real")private Integer siSoReal;
    @Column(name = "mo_ta") private String mota;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mhkh_id", referencedColumnName = "id")
    private MonHocKiHoc mhkh;
    @OneToMany(mappedBy = "lhp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BuoiHoc> lichHoc;
}
