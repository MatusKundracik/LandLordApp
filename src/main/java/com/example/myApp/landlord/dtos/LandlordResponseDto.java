package com.example.myApp.landlord.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LandlordResponseDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String permanentResidence;
    private String tin;
}
