package com.example.myApp.landlord.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LandlordResponseDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private String tin;
    private String phoneNumber;
    private String email;
}

