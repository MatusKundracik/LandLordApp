package com.example.rentalManagement.landlord.services;

import com.example.rentalManagement.exception.AccessDeniedException;
import com.example.rentalManagement.exception.LandlordNotFoundException;
import com.example.rentalManagement.exception.TenantNotFoundException;
import com.example.rentalManagement.landlord.dtos.LandlordRequestDto;
import com.example.rentalManagement.landlord.dtos.LandlordResponseDto;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.landlord.mapper.LandlordMapper;
import com.example.rentalManagement.landlord.repository.LandlordRepository;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LandlordServiceImpl implements LandlordService {

  private final LandlordMapper landlordMapper;
  private final LandlordRepository landlordRepository;
  private final UserRepository userRepository;
  private final TenantRepository tenantRepository;
  private final TenantMapper tenantMapper;

  @Override
  public LandlordResponseDto getMyProfile(String email) {
    return landlordMapper.toDto(getLandlordByEmail(email));
  }

  @Override
  public LandlordResponseDto updateMyProfile(LandlordRequestDto dto, String email) {
    Landlord landlord = getLandlordByEmail(email);

    if (dto.getName() != null) landlord.setName(dto.getName());
    if (dto.getSurname() != null) landlord.setSurname(dto.getSurname());
    if (dto.getDateOfBirth() != null) landlord.setDateOfBirth(dto.getDateOfBirth());
    if (dto.getStreet() != null) landlord.setStreet(dto.getStreet());
    if (dto.getStreetNumber() != null) landlord.setStreetNumber(dto.getStreetNumber());
    if (dto.getCity() != null) landlord.setCity(dto.getCity());
    if (dto.getPostalCode() != null) landlord.setPostalCode(dto.getPostalCode());
    if (dto.getCountry() != null) landlord.setCountry(dto.getCountry());
    if (dto.getTin() != null) landlord.setTin(dto.getTin());
    if (dto.getPhoneNumber() != null) landlord.setPhoneNumber(dto.getPhoneNumber());

    return landlordMapper.toDto(landlordRepository.save(landlord));
  }

  @Override
  public void deleteMyProfile(String email) {
    Landlord landlord = getLandlordByEmail(email);
    userRepository.delete(landlord.getUser());
  }

  @Override
  public Landlord getLandlordByEmail(String email) {
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    return landlordRepository
        .findByUserId(user.getId())
        .orElseThrow(LandlordNotFoundException::new);
  }

  @Override
  public TenantResponseDto createTenant(TenantRequestDto tenantRequestDto) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    Landlord landlord = getLandlordByEmail(email);

    if (userRepository.existsByEmail(tenantRequestDto.getEmail())) {
      throw new RuntimeException("User with this email already exists");
    }

    if (tenantRepository.existsByEmail(tenantRequestDto.getEmail())) {
      throw new RuntimeException("User with this email already exists");
    }

    Tenant tenant = tenantMapper.toEntity(tenantRequestDto, null, landlord);
    Tenant saved = tenantRepository.save(tenant);

    return tenantMapper.toDto(saved);
  }

  @Override
  public List<TenantResponseDto> getAllLandlordTenants(String email) {

    Landlord landlord = getLandlordByEmail(email);

    List<Tenant> listOfTenants = tenantRepository.findAllByLandlord(landlord);

    return listOfTenants.stream().map(tenantMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public void deleteTenant(String email, Long id) {
    Landlord landlord = getLandlordByEmail(email);

    Tenant tenant = tenantRepository.findById(id).orElseThrow(TenantNotFoundException::new);

    if (tenant.getLandlord() == null || !tenant.getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }

    tenantRepository.delete(tenant);
  }

  @Override
  public TenantResponseDto updateTenant(TenantRequestDto requestDto, Long id, String email) {
    Landlord landlord = getLandlordByEmail(email);

    Tenant tenant = tenantRepository.findById(id).orElseThrow(TenantNotFoundException::new);

    if (tenant.getLandlord() == null || !tenant.getLandlord().getId().equals(landlord.getId())) {
      throw new AccessDeniedException();
    }

    if (requestDto.getName() != null) tenant.setName(requestDto.getName());
    if (requestDto.getSurname() != null) tenant.setSurname(requestDto.getSurname());
    if (requestDto.getDateOfBirth() != null) tenant.setDateOfBirth(requestDto.getDateOfBirth());
    if (requestDto.getStreet() != null) tenant.setStreet(requestDto.getStreet());
    if (requestDto.getStreetNumber() != null) tenant.setStreetNumber(requestDto.getStreetNumber());
    if (requestDto.getCity() != null) tenant.setCity(requestDto.getCity());
    if (requestDto.getPostalCode() != null) tenant.setPostalCode(requestDto.getPostalCode());
    if (requestDto.getCountry() != null) tenant.setCountry(requestDto.getCountry());
    if (requestDto.getPhoneNumber() != null) tenant.setPhoneNumber(requestDto.getPhoneNumber());
    if (requestDto.getEmail() != null) tenant.setEmail(requestDto.getEmail());

    return tenantMapper.toDto(tenantRepository.save(tenant));
  }
}
