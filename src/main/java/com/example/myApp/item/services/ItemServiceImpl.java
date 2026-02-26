package com.example.myApp.item.services;

import com.example.myApp.apartment.entity.Apartment;
import com.example.myApp.apartment.repository.ApartmentRepository;
import com.example.myApp.item.dtos.ItemRequestDto;
import com.example.myApp.item.dtos.ItemResponseDto;
import com.example.myApp.item.entity.Item;
import com.example.myApp.item.mapper.ItemMapper;
import com.example.myApp.item.repository.ItemRepository;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.landlord.repository.LandlordRepository;
import com.example.myApp.user.entity.User;
import com.example.myApp.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;
  private final ApartmentRepository apartmentRepository;
  private final LandlordRepository landlordRepository;
  private final UserRepository userRepository;
  private final ItemMapper itemMapper;

  private Landlord getAuthenticatedLandlord() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    return landlordRepository
        .findByUser(user)
        .orElseThrow(() -> new RuntimeException("Landlord not found"));
  }

  private Apartment getApartmentForLandlord(long apartmentId, Landlord landlord) {
    Apartment apartment =
        apartmentRepository
            .findById(apartmentId)
            .orElseThrow(() -> new RuntimeException("Apartment not found"));
    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new RuntimeException("Access denied");
    }
    return apartment;
  }

  @Override
  public ItemResponseDto createItem(ItemRequestDto itemRequestDto) {
    Landlord landlord = getAuthenticatedLandlord();
    Apartment apartment = getApartmentForLandlord(itemRequestDto.getApartmentId(), landlord);

    Item item = itemMapper.toEntity(itemRequestDto);
    item.setApartment(apartment);

    return itemMapper.toDto(itemRepository.save(item));
  }

  @Override
  public ItemResponseDto getItemById(long id) { // ← Long → long
    Landlord landlord = getAuthenticatedLandlord();

    Item item =
        itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

    if (!item.getApartment().getLandlord().getId().equals(landlord.getId())) {
      throw new RuntimeException("Access denied");
    }

    return itemMapper.toDto(item);
  }

  @Override
  public List<ItemResponseDto> getAllItemsByApartment(long apartmentId) {
    Landlord landlord = getAuthenticatedLandlord();
    Apartment apartment = getApartmentForLandlord(apartmentId, landlord);

    return itemRepository.findAllByApartment(apartment).stream()
        .map(itemMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public ItemResponseDto updateItem(long id, ItemRequestDto itemRequestDto) {
    Landlord landlord = getAuthenticatedLandlord();

    Item item =
        itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

    if (!item.getApartment().getLandlord().getId().equals(landlord.getId())) {
      throw new RuntimeException("Access denied");
    }

    if (itemRequestDto.getName() != null) item.setName(itemRequestDto.getName());
    if (itemRequestDto.getDescription() != null)
      item.setDescription(itemRequestDto.getDescription());
    if (itemRequestDto.getQuantity() != null) item.setQuantity(itemRequestDto.getQuantity());
    if (itemRequestDto.getValue() != null) item.setValue(itemRequestDto.getValue());

    return itemMapper.toDto(itemRepository.save(item));
  }

  @Override
  public void deleteItem(long id) {
    Landlord landlord = getAuthenticatedLandlord();

    Item item =
        itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

    if (!item.getApartment().getLandlord().getId().equals(landlord.getId())) {
      throw new RuntimeException("Access denied");
    }

    itemRepository.delete(item);
  }
}
