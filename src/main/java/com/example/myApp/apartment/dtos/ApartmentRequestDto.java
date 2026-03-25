package com.example.myApp.apartment.dtos;

import com.example.myApp.apartment.entity.UnitType;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentRequestDto {
  private String street;
  private String streetNumber;
  private String city;
  private String postalCode;
  private String apartmentNumber;
  private Integer floor;
  private BigDecimal areaSqm;
  private String buildingRegNumber;
  private String cadastralArea;
  private String titleDeedNumber;
  private UnitType unitType;
}
