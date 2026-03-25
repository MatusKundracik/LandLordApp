package com.example.rentalManagement.item.services;

import com.example.rentalManagement.item.dtos.ItemRequestDto;
import com.example.rentalManagement.item.dtos.ItemResponseDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {

    ItemResponseDto createItem(Long apartmentId, ItemRequestDto requestDto, MultipartFile file, String email);

    ItemResponseDto getItemById(Long id);

  ItemResponseDto updateItem(Long id, ItemRequestDto itemRequestDto);

  void deleteItem(Long id);

  List<ItemResponseDto> getAllItemsByApartmentForUser(Long apartmentId, String email);
}
