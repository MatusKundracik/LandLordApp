package com.example.rentalManagement.landlord.repository;

import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandlordRepository extends JpaRepository<Landlord, Long> {
  boolean existsByTin(String tin);

  Optional<Landlord> findByUser(User user);

  Optional<Landlord> findByUserId(Long id);

  boolean existsByUserEmail(String email);
}
