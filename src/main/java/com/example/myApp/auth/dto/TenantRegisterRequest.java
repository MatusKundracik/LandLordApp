package com.example.myApp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantRegisterRequest {

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 6, message = "Password must be at least 6 characters")
  private String password;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Surname is required")
  private String surname;

  private LocalDate dateOfBirth;
  private String street;
  private String streetNumber;
  private String city;
  private String postalCode;
  private String country;
  private String phoneNumber;
}
