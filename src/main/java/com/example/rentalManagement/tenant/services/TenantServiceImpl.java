package com.example.rentalManagement.tenant.services;

import com.example.rentalManagement.apartment.entity.Apartment;
import com.example.rentalManagement.apartment.repository.ApartmentRepository;
import com.example.rentalManagement.exception.AccessDeniedException;
import com.example.rentalManagement.exception.ApartmentNotFoundException;
import com.example.rentalManagement.exception.TenantNotFoundException;
import com.example.rentalManagement.exception.UserNotFoundException;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.landlord.repository.LandlordRepository;
import com.example.rentalManagement.landlord.services.LandlordService;
import com.example.rentalManagement.tenant.dtos.TenantRequestDto;
import com.example.rentalManagement.tenant.dtos.TenantResponseDto;
import com.example.rentalManagement.tenant.entity.Tenant;
import com.example.rentalManagement.tenant.mapper.TenantMapper;
import com.example.rentalManagement.tenant.repository.TenantRepository;
import com.example.rentalManagement.user.entity.User;
import com.example.rentalManagement.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

  private final TenantRepository tenantRepository;
  private final UserRepository userRepository;
  private final TenantMapper tenantMapper;
  private final LandlordRepository landlordRepository;
  private final LandlordService landlordService;
  private final ApartmentRepository apartmentRepository;

  private User getUserByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
  }

  private Tenant getTenantByUser(User user) {
    return tenantRepository.findByUserId(user.getId()).orElseThrow(TenantNotFoundException::new);
  }

  @Override
  @Transactional(readOnly = true)
  public TenantResponseDto getMyProfile(String email) {
    User user = getUserByEmail(email);
    Tenant tenant = getTenantByUser(user);

    return tenantMapper.toDto(tenant);
  }

  @Override
  @Transactional
  public TenantResponseDto updateMyProfile(TenantRequestDto request, String email) {
    User user = getUserByEmail(email);
    Tenant tenant = getTenantByUser(user);

    tenant.setName(request.getName());
    tenant.setSurname(request.getSurname());
    tenant.setDateOfBirth(request.getDateOfBirth());
    tenant.setStreet(request.getStreet());
    tenant.setStreetNumber(request.getStreetNumber());
    tenant.setCity(request.getCity());
    tenant.setPostalCode(request.getPostalCode());
    tenant.setCountry(request.getCountry());
    tenant.setPhoneNumber(request.getPhoneNumber());

    Tenant updatedTenant = tenantRepository.save(tenant);
    return tenantMapper.toDto(updatedTenant);
  }

  @Override
  @Transactional
  public void deleteMyProfile(String email) {
    User user = getUserByEmail(email);
    Tenant tenant = getTenantByUser(user);

    tenantRepository.delete(tenant);
    userRepository.delete(user);
  }

  public Tenant getTenantByEmail(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    return tenantRepository.findByUserId(user.getId()).orElseThrow(TenantNotFoundException::new);
  }

  @Override
  public List<TenantResponseDto> getAllTenantsByApartmentId(Long apartmentId, String email) {
    Landlord landlord = landlordService.getLandlordByEmail(email);

    Apartment apartment =
        apartmentRepository.findById(apartmentId).orElseThrow(ApartmentNotFoundException::new);

    if (!apartment.getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }

    return tenantRepository.findAllByApartmentId(apartmentId).stream()
        .map(tenantMapper::toDto)
        .collect(Collectors.toList());
  }
}
