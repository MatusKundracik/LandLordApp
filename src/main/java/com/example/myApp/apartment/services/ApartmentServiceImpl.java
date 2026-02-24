package com.example.myApp.apartment.services;

import com.example.myApp.apartment.dtos.ApartmentRequestDto;
import com.example.myApp.apartment.dtos.ApartmentResponseDto;
import com.example.myApp.apartment.entity.Apartment;
import com.example.myApp.apartment.mapper.ApartmentMapper;
import com.example.myApp.apartment.repository.ApartmentRepository;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.landlord.repository.LandlordRepository;
import com.example.myApp.user.entity.User;
import com.example.myApp.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

  private final UserRepository userRepository;

  private final ApartmentRepository apartmentRepository;

  private final ApartmentMapper apartmentMapper;
  private final LandlordRepository landlordRepository;

  @Override
  public ApartmentResponseDto createApartment(ApartmentRequestDto apartmentRequestDto) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    Landlord landlord =
        landlordRepository
            .findByUser(user)
            .orElseThrow(() -> new RuntimeException("Landlord not found"));

    Apartment apartment = new Apartment();
    apartment = apartmentMapper.toEntity(apartmentRequestDto);
    apartment.setLandlord(landlord);

    Apartment saved = apartmentRepository.save(apartment);

    ApartmentResponseDto response = new ApartmentResponseDto();
    response = apartmentMapper.toDto(apartment);

    return apartmentMapper.toDto(saved);
  }

  @Override
  public ApartmentResponseDto getApartmentById(long id) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    Landlord landlord =
        landlordRepository
            .findByUser(user)
            .orElseThrow(() -> new RuntimeException("Landlord not found"));

    Apartment apartment =
        apartmentRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Apartment not found"));

    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new RuntimeException("Access denied");
    }

    return apartmentMapper.toDto(apartment);
  }

  @Override
  public List<ApartmentResponseDto> getAllApartmentsByLandlord() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    Landlord landlord =
        landlordRepository
            .findByUser(user)
            .orElseThrow(() -> new RuntimeException("Landlord not found"));

    return apartmentRepository.getAllApartmentsByLandlord(landlord).stream()
        .map(apartmentMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public ApartmentResponseDto updateApartment(long id, ApartmentRequestDto apartmentRequestDto) {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    Landlord landlord =
        landlordRepository
            .findByUser(user)
            .orElseThrow(() -> new RuntimeException("Landlord not found"));

    Apartment apartment =
        apartmentRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Apartment not found"));

    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new RuntimeException("Access denied");
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

    return apartmentMapper.toDto(apartment);
  }

  @Override
  public void deleteApartment(long id) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    Landlord landlord =
        landlordRepository
            .findByUser(user)
            .orElseThrow(() -> new RuntimeException("Landlord not found"));

    Apartment apartment =
        apartmentRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Apartment not found"));

    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new RuntimeException("Access denied");
    }

    apartmentRepository.delete(apartment);
  }
}
