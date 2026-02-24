package com.example.myApp.item.services;

import com.example.myApp.item.dtos.ItemRequestDto;
import com.example.myApp.item.dtos.ItemResponseDto;
import java.util.List;

public interface ItemService {

  ItemResponseDto createItem(ItemRequestDto itemRequestDto);

  ItemResponseDto getItemById(long id);

  List<ItemResponseDto> getAllItemsByApartment(long apartmentId);

  ItemResponseDto updateItem(long id, ItemRequestDto itemRequestDto);

  void deleteItem(long id);
}
