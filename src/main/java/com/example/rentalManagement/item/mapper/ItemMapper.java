package com.example.rentalManagement.item.mapper;

import com.example.rentalManagement.item.dtos.ItemRequestDto;
import com.example.rentalManagement.item.dtos.ItemResponseDto;
import com.example.rentalManagement.item.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

  public Item toEntity(ItemRequestDto requestDto) {
    return Item.builder()
        .name(requestDto.getName())
        .description(requestDto.getDescription())
        .quantity(requestDto.getQuantity())
        .value(requestDto.getValue())
        .build();
  }

  public ItemResponseDto toDto(Item item) {
    return ItemResponseDto.builder()
        .id(item.getId())
        .name(item.getName())
        .description(item.getDescription())
        .quantity(item.getQuantity())
        .value(item.getValue())
        .apartmentId(item.getApartment().getId())
        .build();
  }
}
