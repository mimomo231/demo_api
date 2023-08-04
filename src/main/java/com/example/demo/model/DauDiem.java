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
@Table(name = "dau_diem")
public class DauDiem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ten;
    @Column(name = "ti_le")
    private Float tiLe;
    @Column(name = "mo_ta") private String mota;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mh_id", referencedColumnName = "id")
    private MonHoc monHoc;
}
