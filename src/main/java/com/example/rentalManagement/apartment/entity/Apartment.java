package com.example.rentalManagement.apartment.entity;

import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.shared.AuditableEntity;
import com.example.rentalManagement.tenant.entity.Tenant;
import jakarta.persistence.*;
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
public class Apartment extends AuditableEntity {

  private String street;
  private String streetNumber;
  private String city;
  private String postalCode;
  private String apartmentNumber; // "64"
  private Integer floor; // 4
  private Double areaSqm;
  private String buildingRegNumber; // Súpisné číslo: "1672"
  private String cadastralArea; // "Letná"
  private String titleDeedNumber; // LV: "12162"

  @Enumerated(EnumType.STRING)
  private UnitType unitType;

  // enum: APARTMENT, ROOM

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "landlord_id", nullable = false)
  private Landlord landlord;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tenant_id", nullable = true)
  private Tenant tenant;
}
