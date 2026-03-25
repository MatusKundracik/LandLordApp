package com.example.rentalManagement.landlord.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private String streetNumber;
  private String city;
  private String postalCode;
  private String country;
  private String tin;
  private String phoneNumber;
  private String email;
  private String iban;
}
