package com.example.myApp.landlord.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "landlords")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Landlord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private String tin;
}