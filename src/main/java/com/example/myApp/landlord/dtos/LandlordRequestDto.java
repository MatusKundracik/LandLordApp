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
public class LandlordRequestDto {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private String tin;
}
