package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "sinh_vien")
public class SinhVien extends ThanhVien{
    @Column(name = "ma_sinh_vien")
    private String maSinhVien;
}
