package com.example.demo.model;

import javax.persistence.*;

import com.example.demo.model.enumtype.AccountRoleEnum;
import lombok.*;

@Entity
@Table(name = "giang_vien")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiangVien{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String tel;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private AccountRoleEnum role;
}
