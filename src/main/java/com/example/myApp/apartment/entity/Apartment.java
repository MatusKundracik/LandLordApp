package com.example.myApp.apartment.entity;

import com.example.myApp.landlord.entity.Landlord;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apartments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apartment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String street;
  private String streetNumber;
  private String city;
  private String postalCode;
  private String apartmentNumber; // "64"
  private Integer floor; // 4
  private BigDecimal areaSqm; // 52.89
  private String buildingRegNumber; // Súpisné číslo: "1672"
  private String cadastralArea; // "Letná"
  private String titleDeedNumber; // LV: "12162"

  @Enumerated(EnumType.STRING)
  private UnitType unitType;

  // enum: WHOLE_APARTMENT / ROOM_LEFT / ROOM_RIGHT

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "landlord_id", nullable = false)
  private Landlord landlord; // @ManyToOne
}
