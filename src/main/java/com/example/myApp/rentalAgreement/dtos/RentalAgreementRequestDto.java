package com.example.myApp.rentalAgreement.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalAgreementRequestDto {

  @NotNull(message = "Tenant ID is required")
  private Long tenantId;

  @NotNull(message = "Apartment ID is required")
  private Long apartmentId;

  @NotNull(message = "Start date is required")
  private LocalDate startDate;

  private LocalDate endDate;

  @NotNull(message = "Rent amount is required")
  @DecimalMin(value = "0.0", inclusive = false, message = "Rent amount must be greater than zero")
  private BigDecimal rentAmount;

  @NotNull(message = "Utilities deposit is required")
  @DecimalMin(value = "0.0", message = "Utilities deposit cannot be negative")
  private BigDecimal utilitiesDeposit;

  @NotNull(message = "Security deposit is required")
  @DecimalMin(value = "0.0", message = "Security deposit cannot be negative")
  private BigDecimal securityDeposit;

  @NotNull(message = "Payment day of month is required")
  @Min(value = 1, message = "Payment day must be between 1 and 31")
  @Max(value = 31, message = "Payment day must be between 1 and 31")
  private Integer paymentDayOfMonth;

  @NotBlank(message = "IBAN is required")
  private String iban;

  @NotNull(message = "Penalty rate per day is required")
  @DecimalMin(value = "0.0", message = "Penalty rate cannot be negative")
  private BigDecimal penaltyRatePerDay;

  @NotNull(message = "Signed date is required")
  private LocalDate signedDate;
}
