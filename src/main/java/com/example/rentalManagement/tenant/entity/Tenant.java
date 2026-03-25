package com.example.rentalManagement.tenant.entity;

import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.shared.AuditableEntity;
import com.example.rentalManagement.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "tenants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tenant extends AuditableEntity {

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String street;
    private String streetNumber;
    private String city;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landlord_id", nullable = true)
    private Landlord landlord;
}

