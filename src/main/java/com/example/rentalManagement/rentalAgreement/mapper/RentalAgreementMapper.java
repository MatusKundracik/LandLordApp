package com.example.rentalManagement.rentalAgreement.mapper;

import com.example.rentalManagement.apartment.entity.Apartment;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.rentalAgreement.dtos.RentalAgreementRequestDto;
import com.example.rentalManagement.rentalAgreement.dtos.RentalAgreementResponseDto;
import com.example.rentalManagement.rentalAgreement.entity.ContractStatus;
import com.example.rentalManagement.rentalAgreement.entity.RentalAgreement;
import com.example.rentalManagement.tenant.entity.Tenant;
import org.springframework.stereotype.Component;

@Component
public class RentalAgreementMapper {
  public RentalAgreement toEntity(
      RentalAgreementRequestDto request, Apartment apartment, Tenant tenant, Landlord landlord) {

    return RentalAgreement.builder()
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .rentAmount(request.getRentAmount())
        .utilitiesDeposit(request.getUtilitiesDeposit())
        .securityDeposit(request.getSecurityDeposit())
        .paymentDayOfMonth(request.getPaymentDayOfMonth())
        .iban(request.getIban())
        .penaltyRatePerDay(request.getPenaltyRatePerDay())
        .signedDate(request.getSignedDate())
        .status(ContractStatus.ACTIVE)
        .apartment(apartment)
        .tenant(tenant)
        .landlord(landlord)
        .build();
  }

  public RentalAgreementResponseDto toDto(RentalAgreement agreement) {
    return RentalAgreementResponseDto.builder()
        .id(agreement.getId())
        .apartmentId(agreement.getApartment().getId())
        .tenantId(agreement.getTenant().getId())
        .landlordId(agreement.getLandlord().getId())
        .startDate(agreement.getStartDate())
        .endDate(agreement.getEndDate())
        .rentAmount(agreement.getRentAmount())
        .utilitiesDeposit(agreement.getUtilitiesDeposit())
        .securityDeposit(agreement.getSecurityDeposit())
        .paymentDayOfMonth(agreement.getPaymentDayOfMonth())
        .iban(agreement.getIban())
        .penaltyRatePerDay(agreement.getPenaltyRatePerDay())
        .signedDate(agreement.getSignedDate())
        .status(agreement.getStatus())
        .createdAt(agreement.getCreatedAt())
        .updatedAt(agreement.getUpdatedAt())
        .build();
  }
}
