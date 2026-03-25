package com.example.myApp.landlord.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LandlordRequestDto {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String permanentResidence;
    private String tin;
}
