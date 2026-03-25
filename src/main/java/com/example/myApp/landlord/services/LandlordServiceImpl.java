package com.example.myApp.landlord.services;

import com.example.myApp.exception.LandlordNotFoundException;
import com.example.myApp.landlord.dtos.LandlordRequestDto;
import com.example.myApp.landlord.dtos.LandlordResponseDto;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.landlord.mapper.LandlordMapper;
import com.example.myApp.landlord.repository.LandlordRepository;
import com.example.myApp.user.entity.User;
import com.example.myApp.user.repository.UserRepository;
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
    landlordRepository.delete(landlord);
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
