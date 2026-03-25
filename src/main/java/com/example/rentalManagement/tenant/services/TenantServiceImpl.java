package com.example.rentalManagement.tenant.services;

import com.example.rentalManagement.exception.TenantNotFoundException;
import com.example.rentalManagement.tenant.dtos.TenantRequestDto;
import com.example.rentalManagement.tenant.dtos.TenantResponseDto;
import com.example.rentalManagement.tenant.entity.Tenant;
import com.example.rentalManagement.tenant.mapper.TenantMapper;
import com.example.rentalManagement.tenant.repository.TenantRepository;
import com.example.rentalManagement.user.entity.User;
import com.example.rentalManagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

  private final TenantRepository tenantRepository;
  private final UserRepository userRepository;
    private final TenantMapper tenantMapper;

    private User getUserByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
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
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    return tenantRepository.findByUserId(user.getId()).orElseThrow(TenantNotFoundException::new);
  }
}
