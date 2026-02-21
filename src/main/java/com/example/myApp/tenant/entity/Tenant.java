package com.example.myApp.tenant.entity;

import com.example.myApp.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "tenants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String street;
    private String streetNumber;
    private String city;
    private String postalCode;
    private String country;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

