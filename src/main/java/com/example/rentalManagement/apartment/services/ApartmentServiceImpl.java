package com.example.rentalManagement.apartment.services;

import com.example.rentalManagement.apartment.dtos.ApartmentRequestDto;
import com.example.rentalManagement.apartment.dtos.ApartmentResponseDto;
import com.example.rentalManagement.apartment.entity.Apartment;
import com.example.rentalManagement.apartment.mapper.ApartmentMapper;
import com.example.rentalManagement.apartment.repository.ApartmentRepository;
import com.example.rentalManagement.exception.AccessDeniedException;
import com.example.rentalManagement.exception.ApartmentNotFoundException;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.landlord.repository.LandlordRepository;
import com.example.rentalManagement.landlord.services.LandlordService;
import com.example.rentalManagement.rentalAgreement.entity.ContractStatus;
import com.example.rentalManagement.rentalAgreement.repository.RentalAgreementRepository;
import com.example.rentalManagement.tenant.entity.Tenant;
import com.example.rentalManagement.tenant.services.TenantService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

  private final ApartmentRepository apartmentRepository;
  private final ApartmentMapper apartmentMapper;
  private final LandlordService landlordService;
  private final TenantService tenantService;
  private final LandlordRepository landlordRepository;
  private final RentalAgreementRepository rentalAgreementRepository;

  @Override
  public ApartmentResponseDto createApartment(ApartmentRequestDto apartmentRequestDto) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    Landlord landlord = landlordService.getLandlordByEmail(email);

    Apartment apartment = apartmentMapper.toEntity(apartmentRequestDto);
    apartment.setLandlord(landlord);

    Apartment saved = apartmentRepository.save(apartment);

    return apartmentMapper.toDto(saved);
  }

  @Override
  public ApartmentResponseDto getApartmentById(long id) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    Landlord landlord = landlordService.getLandlordByEmail(email);

    Apartment apartment =
        apartmentRepository.findById(id).orElseThrow(ApartmentNotFoundException::new);

    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }

    return apartmentMapper.toDto(apartment);
  }

  @Override
  public List<ApartmentResponseDto> getAllApartments(String email) {
    if (landlordRepository.existsByUserEmail(email)) {
      Landlord landlord = landlordService.getLandlordByEmail(email);
      return apartmentRepository.getAllApartmentsByLandlord(landlord).stream()
          .map(apartmentMapper::toDto)
          .collect(Collectors.toList());
    } else {
      Tenant tenant = tenantService.getTenantByEmail(email);
      return rentalAgreementRepository.findByTenantAndStatus(tenant, ContractStatus.ACTIVE).stream()
          .map(agreement -> apartmentMapper.toDto(agreement.getApartment()))
          .collect(Collectors.toList());
    }
  }

  @Override
  public ApartmentResponseDto updateApartment(long id, ApartmentRequestDto apartmentRequestDto) {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    Landlord landlord = landlordService.getLandlordByEmail(email);

    Apartment apartment =
        apartmentRepository.findById(id).orElseThrow(ApartmentNotFoundException::new);

    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }

    if (apartmentRequestDto.getStreet() != null)
      apartment.setStreet(apartmentRequestDto.getStreet());
    if (apartmentRequestDto.getStreetNumber() != null)
      apartment.setStreetNumber(apartmentRequestDto.getStreetNumber());
    if (apartmentRequestDto.getCity() != null) apartment.setCity(apartmentRequestDto.getCity());
    if (apartmentRequestDto.getPostalCode() != null)
      apartment.setPostalCode(apartmentRequestDto.getPostalCode());
    if (apartmentRequestDto.getApartmentNumber() != null)
      apartment.setApartmentNumber(apartmentRequestDto.getApartmentNumber());
    if (apartmentRequestDto.getFloor() != null) apartment.setFloor(apartmentRequestDto.getFloor());
    if (apartmentRequestDto.getAreaSqm() != null)
      apartment.setAreaSqm(apartmentRequestDto.getAreaSqm());
    if (apartmentRequestDto.getBuildingRegNumber() != null)
      apartment.setBuildingRegNumber(apartmentRequestDto.getBuildingRegNumber());
    if (apartmentRequestDto.getCadastralArea() != null)
      apartment.setCadastralArea(apartmentRequestDto.getCadastralArea());
    if (apartmentRequestDto.getTitleDeedNumber() != null)
      apartment.setTitleDeedNumber(apartmentRequestDto.getTitleDeedNumber());
    if (apartmentRequestDto.getUnitType() != null)
      apartment.setUnitType(apartmentRequestDto.getUnitType());
    apartmentRepository.save(apartment);

    return apartmentMapper.toDto(apartmentRepository.save(apartment));
  }

  @Override
  public void deleteApartment(long id) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    Landlord landlord = landlordService.getLandlordByEmail(email);

    Apartment apartment =
        apartmentRepository.findById(id).orElseThrow(ApartmentNotFoundException::new);

    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }

    apartmentRepository.delete(apartment);
  }
}
