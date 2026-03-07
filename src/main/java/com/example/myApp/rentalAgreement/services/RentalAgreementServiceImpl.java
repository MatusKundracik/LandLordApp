package com.example.myApp.rentalAgreement.services;

import com.example.myApp.apartment.entity.Apartment;
import com.example.myApp.apartment.repository.ApartmentRepository;
import com.example.myApp.exception.AccessDeniedException;
import com.example.myApp.exception.ApartmentNotFoundException;
import com.example.myApp.exception.RentalAgreementNotFoundException;
import com.example.myApp.exception.TenantNotFoundException;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.landlord.repository.LandlordRepository;
import com.example.myApp.landlord.services.LandlordService;
import com.example.myApp.rentalAgreement.dtos.RentalAgreementRequestDto;
import com.example.myApp.rentalAgreement.dtos.RentalAgreementResponseDto;
import com.example.myApp.rentalAgreement.entity.RentalAgreement;
import com.example.myApp.rentalAgreement.mapper.RentalAgreementMapper;
import com.example.myApp.rentalAgreement.repository.RentalAgreementRepository;
import com.example.myApp.tenant.entity.Tenant;
import com.example.myApp.tenant.repository.TenantRepository;
import com.example.myApp.tenant.services.TenantService;
import com.example.myApp.user.repository.UserRepository;
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

  public RentalAgreementResponseDto createRentalAgreement(
      RentalAgreementRequestDto requestDto, String email) {
    Landlord landlord = landlordService.getLandlordByEmail(email);

    Tenant tenant =
        tenantRepository
            .findById(requestDto.getTenantId())
            .orElseThrow(TenantNotFoundException::new);

    Apartment apartment =
        apartmentRepository
            .findById(requestDto.getApartmentId())
            .orElseThrow(ApartmentNotFoundException::new);

    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }

    RentalAgreement rentalAgreement =
        rentalAgreementMapper.toEntity(requestDto, apartment, tenant, landlord);
    RentalAgreement saved = rentalAgreementRepository.save(rentalAgreement);

    return rentalAgreementMapper.toDto(saved);
  }

  public RentalAgreementResponseDto updateRentalAgreement(
      long id, RentalAgreementRequestDto requestDto, String email) {

    Landlord landlord = landlordService.getLandlordByEmail(email);

    RentalAgreement agreement =
        rentalAgreementRepository.findById(id).orElseThrow(RentalAgreementNotFoundException::new);

    if (!agreement.getLandlord().getId().equals(landlord.getId()))
      throw new AccessDeniedException();

    if (requestDto.getRentAmount() != null) agreement.setRentAmount(requestDto.getRentAmount());
    if (requestDto.getEndDate() != null) agreement.setEndDate(requestDto.getEndDate());
    if (requestDto.getPaymentDayOfMonth() != null)
      agreement.setPaymentDayOfMonth(requestDto.getPaymentDayOfMonth());
    if (requestDto.getIban() != null) agreement.setIban(requestDto.getIban());
    if (requestDto.getUtilitiesDeposit() != null)
      agreement.setUtilitiesDeposit(requestDto.getUtilitiesDeposit());
    if (requestDto.getSecurityDeposit() != null)
      agreement.setSecurityDeposit(requestDto.getSecurityDeposit());
    if (requestDto.getPenaltyRatePerDay() != null)
      agreement.setPenaltyRatePerDay(requestDto.getPenaltyRatePerDay());

    return rentalAgreementMapper.toDto(rentalAgreementRepository.save(agreement));
  }

  public RentalAgreementResponseDto getRentalAgreement(long id, String email) {
    Landlord landlord = landlordService.getLandlordByEmail(email);

    RentalAgreement agreement =
        rentalAgreementRepository.findById(id).orElseThrow(RentalAgreementNotFoundException::new);

    if (!agreement.getLandlord().getId().equals(landlord.getId()))
      throw new AccessDeniedException();

    return rentalAgreementMapper.toDto(agreement);
  }

  public List<RentalAgreementResponseDto> getRentalAgreementsByLandlord(String email) {
    Landlord landlord = landlordService.getLandlordByEmail(email);

    return rentalAgreementRepository.getAllRentalAgreementsByLandlord(landlord).stream()
        .map(rentalAgreementMapper::toDto)
        .collect(Collectors.toList());
  }

  public void deleteRentalAgreement(long id, String email) {

    Landlord landlord = landlordService.getLandlordByEmail(email);

    RentalAgreement agreement =
        rentalAgreementRepository.findById(id).orElseThrow(RentalAgreementNotFoundException::new);

    if (!agreement.getLandlord().getId().equals(landlord.getId()))
      throw new AccessDeniedException();

    rentalAgreementRepository.deleteById(id);
  }

  @Override
  public List<RentalAgreementResponseDto> getRentalAgreementsByTenant(String email) {
    Tenant tenant = tenantService.getTenantByEmail(email);

    return rentalAgreementRepository.getAllRentalAgreementsByTenant(tenant).stream()
        .map(rentalAgreementMapper::toDto)
        .collect(Collectors.toList());
  }
}
