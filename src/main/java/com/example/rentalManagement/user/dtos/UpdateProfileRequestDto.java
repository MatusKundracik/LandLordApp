package com.example.rentalManagement.user.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileRequestDto {
  private String name;
  private String surname;
  private LocalDate dateOfBirth;
  private String street;
  private String streetNumber;
  private String city;
  private String postalCode;
  private String country;
  private String phoneNumber;
  private String tin;
  private String iban;
}
