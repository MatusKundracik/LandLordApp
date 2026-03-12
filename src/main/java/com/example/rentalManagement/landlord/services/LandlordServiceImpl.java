package com.example.rentalManagement.landlord.services;

import com.example.rentalManagement.exception.LandlordNotFoundException;
import com.example.rentalManagement.landlord.dtos.LandlordRequestDto;
import com.example.rentalManagement.landlord.dtos.LandlordResponseDto;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.landlord.mapper.LandlordMapper;
import com.example.rentalManagement.landlord.repository.LandlordRepository;
import com.example.rentalManagement.user.entity.User;
import com.example.rentalManagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LandlordServiceImpl implements LandlordService {

  private final LandlordMapper landlordMapper;
  private final LandlordRepository landlordRepository;
  private final UserRepository userRepository;

  @Override
  public LandlordResponseDto getMyProfile(String email) {
    return landlordMapper.toDto(getLandlordByEmail(email));
  }

  @Override
  public LandlordResponseDto updateMyProfile(LandlordRequestDto dto, String email) {
    Landlord landlord = getLandlordByEmail(email);

    if (dto.getName() != null) landlord.setName(dto.getName());
    if (dto.getSurname() != null) landlord.setSurname(dto.getSurname());
    if (dto.getDateOfBirth() != null) landlord.setDateOfBirth(dto.getDateOfBirth());
    if (dto.getStreet() != null) landlord.setStreet(dto.getStreet());
    if (dto.getStreetNumber() != null) landlord.setStreetNumber(dto.getStreetNumber());
    if (dto.getCity() != null) landlord.setCity(dto.getCity());
    if (dto.getPostalCode() != null) landlord.setPostalCode(dto.getPostalCode());
    if (dto.getCountry() != null) landlord.setCountry(dto.getCountry());
    if (dto.getTin() != null) landlord.setTin(dto.getTin());
    if (dto.getPhoneNumber() != null) landlord.setPhoneNumber(dto.getPhoneNumber());

    return landlordMapper.toDto(landlordRepository.save(landlord));
  }

  @Override
  public void deleteMyProfile(String email) {
    Landlord landlord = getLandlordByEmail(email);
    userRepository.delete(landlord.getUser());
  }

  @Override
  public Landlord getLandlordByEmail(String email) {
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    return landlordRepository
        .findByUserId(user.getId())
        .orElseThrow(LandlordNotFoundException::new);
  }
}
