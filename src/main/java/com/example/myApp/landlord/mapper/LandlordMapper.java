package com.example.myApp.landlord.mapper;

import com.example.myApp.landlord.dtos.LandlordRequestDto;
import com.example.myApp.landlord.dtos.LandlordResponseDto;
import com.example.myApp.landlord.entity.Landlord;
import org.springframework.stereotype.Component;
@Component
public class LandlordMapper {

    public LandlordResponseDto toDto(Landlord landlord){
        LandlordResponseDto landlordResponseDto = new LandlordResponseDto();
        landlordResponseDto.setId(landlord.getId());
        landlordResponseDto.setName(landlord.getName());
        landlordResponseDto.setSurname(landlord.getSurname());
        landlordResponseDto.setDateOfBirth(landlord.getDateOfBirth());
        landlordResponseDto.setPermanentResidence(landlord.getPermanentResidence());
        landlordResponseDto.setTin(landlord.getTin());
        return  landlordResponseDto;
    }

    public Landlord toEntity(LandlordRequestDto dto) {
        Landlord landlord = new Landlord();
        landlord.setName(dto.getName());
        landlord.setSurname(dto.getSurname());
        landlord.setDateOfBirth(dto.getDateOfBirth());
        landlord.setPermanentResidence(dto.getPermanentResidence());
        landlord.setTin(dto.getTin());
        return landlord;
    }

}
