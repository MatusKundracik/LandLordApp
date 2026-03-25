package com.example.rentalManagement.item.services;

import com.example.rentalManagement.apartment.entity.Apartment;
import com.example.rentalManagement.apartment.repository.ApartmentRepository;
import com.example.rentalManagement.cloudinary.CloudinaryService;
import com.example.rentalManagement.exception.*;
import com.example.rentalManagement.item.dtos.ItemRequestDto;
import com.example.rentalManagement.item.dtos.ItemResponseDto;
import com.example.rentalManagement.item.entity.Item;
import com.example.rentalManagement.item.mapper.ItemMapper;
import com.example.rentalManagement.item.repository.ItemRepository;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.landlord.repository.LandlordRepository;
import com.example.rentalManagement.landlord.services.LandlordService;
import com.example.rentalManagement.rentalAgreement.entity.ContractStatus;
import com.example.rentalManagement.rentalAgreement.repository.RentalAgreementRepository;
import com.example.rentalManagement.tenant.entity.Tenant;
import com.example.rentalManagement.tenant.services.TenantService;
import com.example.rentalManagement.user.entity.User;
import com.example.rentalManagement.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
  private final CloudinaryService cloudinaryService;
  private final LandlordService landlordService;

  private Landlord getAuthenticatedLandlord() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    return landlordRepository.findByUser(user).orElseThrow(LandlordNotFoundException::new);
  }

  private Apartment getApartmentForLandlord(Long apartmentId, Landlord landlord) {
    Apartment apartment =
        apartmentRepository.findById(apartmentId).orElseThrow(ItemNotFoundException::new);
    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }
    return apartment;
  }

  @Override
  public ItemResponseDto createItem(
      Long apartmentId, ItemRequestDto itemRequestDto, MultipartFile file, String email) {
    Landlord landlord = getAuthenticatedLandlord();
    Apartment apartment = getApartmentForLandlord(apartmentId, landlord);

    Item item = itemMapper.toEntity(itemRequestDto);
    item.setApartment(apartment);
    Item saved = itemRepository.save(item);

    if (file != null && !file.isEmpty()) {
      return uploadImage(saved.getId(), file, email);
    }

    return itemMapper.toDto(saved);
  }

  @Override
  public ItemResponseDto getItemById(Long id) {
    Landlord landlord = getAuthenticatedLandlord();

    Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);

    if (!item.getApartment().getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }

    return itemMapper.toDto(item);
  }

  @Override
  public ItemResponseDto updateItem(Long id, ItemRequestDto itemRequestDto) {
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
  public void deleteItem(Long id) {
    Landlord landlord = getAuthenticatedLandlord();

    Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);

    if (!item.getApartment().getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }

    itemRepository.delete(item);
  }

  public ItemResponseDto uploadImage(Long id, MultipartFile file, String email) {
    Landlord landlord = landlordService.getLandlordByEmail(email);

    Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);

    if (!item.getApartment().getLandlord().getId().equals(landlord.getId()))
      throw new AccessDeniedException();

    String imageUrl = cloudinaryService.uploadImage(file);
    item.setImageUrl(imageUrl);

    return itemMapper.toDto(itemRepository.save(item));
  }

  @Override
  public List<ItemResponseDto> getAllItemsByApartmentForUser(Long apartmentId, String email) {
    Apartment apartment =
        apartmentRepository.findById(apartmentId).orElseThrow(ApartmentNotFoundException::new);

    if (isLandlord(email)) {
      Landlord landlord = getAuthenticatedLandlord();
      if (!apartment.getLandlord().equals(landlord)) {
        throw new ApartmentNotFoundException();
      }
    } else {
      Tenant tenant = tenantService.getTenantByEmail(email);
      rentalAgreementRepository
          .findByTenantAndApartmentAndStatus(tenant, apartment, ContractStatus.ACTIVE)
          .orElseThrow(RentalAgreementNotFoundException::new);
    }

    return itemRepository.findAllByApartment(apartment).stream()
        .map(itemMapper::toDto)
        .collect(Collectors.toList());
  }

  private boolean isLandlord(String email) {
    return landlordRepository.existsByUserEmail(email);
  }
}
