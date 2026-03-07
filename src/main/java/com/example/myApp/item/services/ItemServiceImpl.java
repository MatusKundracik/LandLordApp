package com.example.myApp.item.services;

import com.example.myApp.apartment.entity.Apartment;
import com.example.myApp.apartment.repository.ApartmentRepository;
import com.example.myApp.exception.AccessDeniedException;
import com.example.myApp.exception.ApartmentNotFoundException;
import com.example.myApp.exception.ItemNotFoundException;
import com.example.myApp.exception.LandlordNotFoundException;
import com.example.myApp.item.dtos.ItemRequestDto;
import com.example.myApp.item.dtos.ItemResponseDto;
import com.example.myApp.item.entity.Item;
import com.example.myApp.item.mapper.ItemMapper;
import com.example.myApp.item.repository.ItemRepository;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.landlord.repository.LandlordRepository;
import com.example.myApp.rentalAgreement.entity.ContractStatus;
import com.example.myApp.rentalAgreement.repository.RentalAgreementRepository;
import com.example.myApp.tenant.entity.Tenant;
import com.example.myApp.tenant.services.TenantService;
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
  private final TenantService tenantService;
  private final RentalAgreementRepository rentalAgreementRepository;

  private Landlord getAuthenticatedLandlord() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    return landlordRepository.findByUser(user).orElseThrow(LandlordNotFoundException::new);
  }

  private Apartment getApartmentForLandlord(long apartmentId, Landlord landlord) {
    Apartment apartment =
        apartmentRepository.findById(apartmentId).orElseThrow(ItemNotFoundException::new);
    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
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
  public ItemResponseDto getItemById(long id) {
    Landlord landlord = getAuthenticatedLandlord();

    Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);

    if (!item.getApartment().getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
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

    Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);

    if (!item.getApartment().getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
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

    Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);

    if (!item.getApartment().getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }

    itemRepository.delete(item);
  }

  @Override
  public List<ItemResponseDto> getAllItemsForTenantByApartment(long apartmentId, String email) {
    Tenant tenant = tenantService.getTenantByEmail(email);

    Apartment apartment =
        apartmentRepository.findById(apartmentId).orElseThrow(ApartmentNotFoundException::new);

    rentalAgreementRepository
        .findByTenantAndApartmentAndStatus(tenant, apartment, ContractStatus.ACTIVE)
        .orElseThrow(
            () -> new RuntimeException("No active rental agreement found for this apartment"));

    return itemRepository.findAllByApartment(apartment).stream()
        .map(itemMapper::toDto)
        .collect(Collectors.toList());
  }
}
