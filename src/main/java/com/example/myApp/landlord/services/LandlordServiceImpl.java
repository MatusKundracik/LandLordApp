package com.example.myApp.landlord.services;

import com.example.myApp.landlord.dtos.LandlordRequestDto;
import com.example.myApp.landlord.dtos.LandlordResponseDto;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.landlord.mapper.LandlordMapper;
import com.example.myApp.landlord.repository.LandlordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LandlordServiceImpl implements LandlordService {

    public final LandlordRepository landlordRepository;
    public final LandlordMapper landlordMapper;

    @Override
    public List<LandlordResponseDto> getAllLandlords() {

        List<Landlord> landLordsList = landlordRepository.findAll();
        List<LandlordResponseDto> responseDtos = new ArrayList<>();
        for (Landlord landlord : landLordsList) {
            responseDtos.add(landlordMapper.toDto(landlord));
        }

        return responseDtos;
    }

    @Override
    public LandlordResponseDto getLandlordById(Long id) {
        Landlord landlord = landlordRepository.findById(id).orElseThrow(() -> new RuntimeException("Landlord not found"));

        return landlordMapper.toDto(landlord);
    }

    @Override
    public LandlordResponseDto createLandlord(LandlordRequestDto dto) {



        Landlord landlord = landlordMapper.toEntity(dto);
        Landlord savedLandlord = landlordRepository.save(landlord);
        return landlordMapper.toDto(savedLandlord);
    }

    @Override
    public LandlordResponseDto updateLandlord(Long id, LandlordRequestDto dto) {
        Landlord landlord = landlordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Landlord not found"));

        landlord.setName(dto.getName());
        landlord.setSurname(dto.getSurname());
        landlord.setDateOfBirth(dto.getDateOfBirth());
        landlord.setStreet(dto.getStreet());
        landlord.setCity(dto.getCity());
        landlord.setPostalCode(dto.getPostalCode());
        landlord.setCountry(dto.getCountry());
        landlord.setTin(dto.getTin());

        landlordRepository.save(landlord);
        return landlordMapper.toDto(landlord);
    }

    @Override
    public void deleteLandlord(Long id) {
    Landlord landlord = landlordRepository.findById(id).orElseThrow(() -> new RuntimeException("Landlord not found"));

    landlordRepository.delete(landlord);
    }
}
