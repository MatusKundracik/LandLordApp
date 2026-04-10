package com.example.rentalManagement.rentalAgreement.services;

import com.example.rentalManagement.apartment.entity.Apartment;
import com.example.rentalManagement.apartment.repository.ApartmentRepository;
import com.example.rentalManagement.exception.*;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.landlord.repository.LandlordRepository;
import com.example.rentalManagement.landlord.services.LandlordService;
import com.example.rentalManagement.rentalAgreement.dtos.RentalAgreementRequestDto;
import com.example.rentalManagement.rentalAgreement.dtos.RentalAgreementResponseDto;
import com.example.rentalManagement.rentalAgreement.entity.RentalAgreement;
import com.example.rentalManagement.rentalAgreement.mapper.RentalAgreementMapper;
import com.example.rentalManagement.rentalAgreement.repository.RentalAgreementRepository;
import com.example.rentalManagement.tenant.entity.Tenant;
import com.example.rentalManagement.tenant.repository.TenantRepository;
import com.example.rentalManagement.tenant.services.TenantService;
import com.example.rentalManagement.user.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalAgreementServiceImpl implements RentalAgreementService {

  private final RentalAgreementMapper rentalAgreementMapper;

  private final RentalAgreementRepository rentalAgreementRepository;
  private final LandlordRepository landlordRepository;
  private final UserRepository userRepository;
  private final ApartmentRepository apartmentRepository;
  private final TenantRepository tenantRepository;
  private final LandlordService landlordService;
  private final TenantService tenantService;

  @Override
  public RentalAgreementResponseDto createRentalAgreement(
      Long apartmentId, RentalAgreementRequestDto requestDto, String email) {

    Landlord landlord = landlordService.getLandlordByEmail(email);

    Apartment apartment =
        apartmentRepository.findById(apartmentId).orElseThrow(ApartmentNotFoundException::new);

    if (!apartment.getLandlord().getId().equals(landlord.getId()))
      throw new AccessDeniedException();

    Tenant tenant =
        tenantRepository.findByApartmentId(apartmentId).orElseThrow(TenantNotFoundException::new);

    // 🔒 Overlap check — excludeId = null, lebo tvoríme nový záznam
    if (rentalAgreementRepository.existsOverlappingAgreement(
        apartment, requestDto.getStartDate(), requestDto.getEndDate(), null)) {
      throw new RentalAgreementConflictException();
    }

    RentalAgreement rentalAgreement =
        rentalAgreementMapper.toEntity(requestDto, apartment, tenant, landlord);
    return rentalAgreementMapper.toDto(rentalAgreementRepository.save(rentalAgreement));
  }

  @Override
  public RentalAgreementResponseDto updateRentalAgreement(
      Long id, RentalAgreementRequestDto requestDto, String email) {

    Landlord landlord = landlordService.getLandlordByEmail(email);

    RentalAgreement agreement =
        rentalAgreementRepository.findById(id).orElseThrow(RentalAgreementNotFoundException::new);

    if (!agreement.getLandlord().getId().equals(landlord.getId()))
      throw new AccessDeniedException();

    LocalDate effectiveStart =
        requestDto.getStartDate() != null ? requestDto.getStartDate() : agreement.getStartDate();
    LocalDate effectiveEnd =
        requestDto.getEndDate() != null ? requestDto.getEndDate() : agreement.getEndDate();

    if (rentalAgreementRepository.existsOverlappingAgreement(
        agreement.getApartment(), effectiveStart, effectiveEnd, agreement.getId())) {
      throw new RentalAgreementConflictException();
    }

    if (requestDto.getRentAmount() != null) agreement.setRentAmount(requestDto.getRentAmount());
    if (requestDto.getStartDate() != null) agreement.setStartDate(requestDto.getStartDate());
    if (requestDto.getEndDate() != null) agreement.setEndDate(requestDto.getEndDate());
    if (requestDto.getPaymentDayOfMonth() != null)
      agreement.setPaymentDayOfMonth(requestDto.getPaymentDayOfMonth());
    if (requestDto.getUtilitiesDeposit() != null)
      agreement.setUtilitiesDeposit(requestDto.getUtilitiesDeposit());
    if (requestDto.getSecurityDeposit() != null)
      agreement.setSecurityDeposit(requestDto.getSecurityDeposit());
    if (requestDto.getPenaltyRatePerDay() != null)
      agreement.setPenaltyRatePerDay(requestDto.getPenaltyRatePerDay());

    return rentalAgreementMapper.toDto(rentalAgreementRepository.save(agreement));
  }

  public RentalAgreementResponseDto getRentalAgreement(Long id, String email) {
    Landlord landlord = landlordService.getLandlordByEmail(email);

    RentalAgreement agreement =
        rentalAgreementRepository.findById(id).orElseThrow(RentalAgreementNotFoundException::new);

    if (!agreement.getLandlord().getId().equals(landlord.getId()))
      throw new AccessDeniedException();

    return rentalAgreementMapper.toDto(agreement);
  }

  public void deleteRentalAgreement(Long id, String email) {

    Landlord landlord = landlordService.getLandlordByEmail(email);

    RentalAgreement agreement =
        rentalAgreementRepository.findById(id).orElseThrow(RentalAgreementNotFoundException::new);

    if (!agreement.getLandlord().getId().equals(landlord.getId()))
      throw new AccessDeniedException();

    rentalAgreementRepository.deleteById(id);
  }

  @Override
  public List<RentalAgreementResponseDto> getRentalAgreements(String email) {
    if (landlordRepository.existsByUserEmail(email)) {
      Landlord landlord = landlordService.getLandlordByEmail(email);
      return rentalAgreementRepository.getAllRentalAgreementsByLandlord(landlord).stream()
          .map(rentalAgreementMapper::toDto)
          .collect(Collectors.toList());
    } else {
      Tenant tenant = tenantService.getTenantByEmail(email);
      return rentalAgreementRepository.getAllRentalAgreementsByTenant(tenant).stream()
          .map(rentalAgreementMapper::toDto)
          .collect(Collectors.toList());
    }
  }
}
