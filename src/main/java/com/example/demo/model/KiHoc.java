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
@Table(name = "ki_hoc")
public class KiHoc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ten;
    @Column(name = "mo_ta") private String mota;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nh_id", referencedColumnName = "id")
    private NamHoc namHoc;
}
