package com.example.demo.model;

import javax.persistence.*;

import com.example.demo.model.enumtype.AccountRoleEnum;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "thanh_vien")
public class ThanhVien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String tel;
    @Column(name = "ma_sinh_vien")
    private String maSinhVien;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private AccountRoleEnum role;
}
