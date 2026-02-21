package com.example.myApp.auth.service;

import com.example.myApp.auth.dto.LandlordRegisterRequest;
import com.example.myApp.auth.dto.LoginRequest;
import com.example.myApp.auth.dto.LoginResponse;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.landlord.repository.LandlordRepository;
import com.example.myApp.security.JwtUtils;
import com.example.myApp.user.entity.Role;
import com.example.myApp.user.entity.User;
import com.example.myApp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final LandlordRepository landlordRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    @Transactional
    public void registerLandlord(LandlordRegisterRequest request) {
        log.info("Registering landlord with email: {}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (landlordRepository.existsByTin(request.getTin())) {
            throw new RuntimeException("TIN already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.LANDLORD)
                .isActive(true)
                .build();

        User savedUser = userRepository.save(user);

        Landlord landlord = Landlord.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .dateOfBirth(request.getDateOfBirth())
                .street(request.getStreet())
                .city(request.getCity())
                .postalCode(request.getPostalCode())
                .country(request.getCountry())
                .tin(request.getTin())
                .phoneNumber(request.getPhoneNumber())
                .user(savedUser)
                .build();

        landlordRepository.save(landlord);

        log.info("Landlord registered successfully");
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        log.info("Logging in user with email: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.isActive()) {
            throw new RuntimeException("Account is not active");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        return LoginResponse.builder()
                .token(token)
                .role(user.getRole().name())
                .build();
    }
}
