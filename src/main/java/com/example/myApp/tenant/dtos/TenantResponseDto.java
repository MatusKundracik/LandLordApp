package com.example.myApp.tenant.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantResponseDto {

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
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
