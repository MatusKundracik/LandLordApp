package com.example.myApp.apartment.dtos;

import com.example.myApp.apartment.entity.UnitType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentRequestDto {

  @NotBlank(message = "Street is required")
  private String street;

  @NotBlank(message = "StreetNumber is required")
  private String streetNumber;

  @NotBlank(message = "City is required")
  private String city;

  @NotBlank(message = "Postal code is required")
  private String postalCode;

  @NotBlank(message = "Apartment number is required")
  private String apartmentNumber;

  @NotNull(message = "Floor is required")
  private Integer floor;

  @NotNull(message = "Area in square meters code is required")
  @Positive
  private Double areaSqm;

  @NotBlank(message = "Building registration number code is required")
  private String buildingRegNumber;

  @NotBlank(message = "Cadastral area is required")
  private String cadastralArea;

  @NotBlank(message = "Title deed number is required")
  private String titleDeedNumber;

  @NotNull(message = "Unit type code is required")
  private UnitType unitType;
}
