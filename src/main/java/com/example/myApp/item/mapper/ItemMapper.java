package com.example.myApp.item.mapper;

import com.example.myApp.item.dtos.ItemRequestDto;
import com.example.myApp.item.dtos.ItemResponseDto;
import com.example.myApp.item.entity.Item;
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
    // apartment sa nenastavuje tu - nastavuje sa v service
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
