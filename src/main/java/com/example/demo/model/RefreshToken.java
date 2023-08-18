package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private ThanhVien user;

    @Column(unique = true)
    private String token;

    @Column(name = "expiry_date")
    private Instant expiryDate;

}
