package com.example.myApp.item.services;

import com.example.myApp.item.dtos.ItemRequestDto;
import com.example.myApp.item.dtos.ItemResponseDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {

  ItemResponseDto createItem(ItemRequestDto itemRequestDto);

  ItemResponseDto getItemById(long id);

  ItemResponseDto updateItem(long id, ItemRequestDto itemRequestDto);

  void deleteItem(long id);

  ItemResponseDto uploadImage(long id, MultipartFile file, String email);

  List<ItemResponseDto> getAllItemsByApartmentForUser(long apartmentId, String email);
}
