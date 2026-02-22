package com.example.myApp.rentalAgreement.entity;

import com.example.myApp.apartment.entity.Apartment;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.tenant.entity.Tenant;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rental_agreements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalAgreement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate startDate; // 01.02.2026
  private LocalDate endDate; // 30.06.2026
  private BigDecimal rentAmount; // 180 €
  private BigDecimal utilitiesDeposit; // 140 €
  private BigDecimal securityDeposit; // 320 €
  private Integer paymentDayOfMonth; // 15
  private String iban;
  private BigDecimal penaltyRatePerDay; // 0.0005 (= 0.05%)
  private LocalDate signedDate; // 31.01.2026

  @Enumerated(EnumType.STRING)
  private ContractStatus status; // enum: ACTIVE / EXPIRED / TERMINATED

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tenant_id", nullable = false)
  private Tenant tenant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "landlord_id", nullable = false)
  private Landlord landlord;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "apartment_id", nullable = false)
  private Apartment apartment;
}
