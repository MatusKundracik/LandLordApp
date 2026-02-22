package com.example.myApp.landlord.services;

import com.example.myApp.landlord.dtos.LandlordRequestDto;
import com.example.myApp.landlord.dtos.LandlordResponseDto;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.landlord.mapper.LandlordMapper;
import com.example.myApp.landlord.repository.LandlordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LandlordServiceImpl implements LandlordService {

  private final LandlordRepository landlordRepository;
  private final LandlordMapper landlordMapper;

  @Override
  public List<LandlordResponseDto> getAllLandlords() {
    return landlordRepository.findAll().stream().map(landlordMapper::toDto).toList();
  }

  @Override
  public LandlordResponseDto getLandlordById(Long id) {
    Landlord landlord =
        landlordRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Landlord not found"));
    return landlordMapper.toDto(landlord);
  }

  @Override
  public LandlordResponseDto updateLandlord(Long id, LandlordRequestDto dto) {
    Landlord landlord =
        landlordRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Landlord not found"));

    landlord.setName(dto.getName());
    landlord.setSurname(dto.getSurname());
    landlord.setDateOfBirth(dto.getDateOfBirth());
    landlord.setStreet(dto.getStreet());
    landlord.setCity(dto.getCity());
    landlord.setPostalCode(dto.getPostalCode());
    landlord.setCountry(dto.getCountry());
    landlord.setTin(dto.getTin());
    landlord.setPhoneNumber(dto.getPhoneNumber()); // ← pridaj

    landlordRepository.save(landlord);
    return landlordMapper.toDto(landlord);
  }

  @Override
  public void deleteLandlord(Long id) {
    Landlord landlord =
        landlordRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Landlord not found"));
    landlordRepository.delete(landlord);
  }
}
