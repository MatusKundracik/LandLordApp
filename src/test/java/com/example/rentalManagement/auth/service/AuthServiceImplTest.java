package com.example.rentalManagement.auth.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.rentalManagement.auth.dto.LandlordRegisterRequest;
import com.example.rentalManagement.auth.dto.LoginRequest;
import com.example.rentalManagement.auth.dto.LoginResponse;
import com.example.rentalManagement.landlord.repository.LandlordRepository;
import com.example.rentalManagement.security.JwtUtils;
import com.example.rentalManagement.tenant.repository.TenantRepository;
import com.example.rentalManagement.user.entity.Role;
import com.example.rentalManagement.user.entity.User;
import com.example.rentalManagement.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

  @Mock private UserRepository userRepository;
  @Mock private LandlordRepository landlordRepository;
  @Mock private TenantRepository tenantRepository;
  @Mock private PasswordEncoder passwordEncoder;
  @Mock private JwtUtils jwtUtils;

  @InjectMocks private AuthServiceImpl authService;

  @Test
  void login_success() {
    User user =
        User.builder()
            .email("test@test.com")
            .password("encodedPassword")
            .role(Role.LANDLORD)
            .isActive(true)
            .build();

    when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));
    when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
    when(jwtUtils.generateToken("test@test.com")).thenReturn("jwt-token");

    LoginResponse response = authService.login(new LoginRequest("test@test.com", "password"));

    assertThat(response.getToken()).isEqualTo("jwt-token");
    assertThat(response.getUserResponseDto().getRole()).isEqualTo("LANDLORD"); // ← opravené
    assertThat(response.getUserResponseDto().getEmail()).isEqualTo("test@test.com");
  }

  @Test
  void login_userNotFound_throwsException() {
    when(userRepository.findByEmail("unknown@test.com")).thenReturn(Optional.empty());

    assertThatThrownBy(() -> authService.login(new LoginRequest("unknown@test.com", "password")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("Invalid email or password");
  }

  @Test
  void login_wrongPassword_throwsException() {
    User user =
        User.builder().email("test@test.com").password("encodedPassword").isActive(true).build();

    when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));
    when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

    assertThatThrownBy(() -> authService.login(new LoginRequest("test@test.com", "wrongPassword")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("Invalid email or password");
  }

  @Test
  void login_inactiveAccount_throwsException() {
    User user =
        User.builder().email("test@test.com").password("encodedPassword").isActive(false).build();

    when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));

    assertThatThrownBy(() -> authService.login(new LoginRequest("test@test.com", "password")))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("Account is not active");
  }

  @Test
  void registerLandlord_emailAlreadyExists_throwsException() {
    when(userRepository.existsByEmail("exists@test.com")).thenReturn(true);

    LandlordRegisterRequest request = new LandlordRegisterRequest();
    request.setEmail("exists@test.com");

    assertThatThrownBy(() -> authService.registerLandlord(request))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("Email already exists");
  }

  @Test
  void registerLandlord_tinAlreadyExists_throwsException() {
    when(userRepository.existsByEmail(any())).thenReturn(false);
    when(landlordRepository.existsByTin("123456")).thenReturn(true);

    LandlordRegisterRequest request = new LandlordRegisterRequest();
    request.setEmail("new@test.com");
    request.setTin("123456");

    assertThatThrownBy(() -> authService.registerLandlord(request))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("TIN already exists");
  }
}
