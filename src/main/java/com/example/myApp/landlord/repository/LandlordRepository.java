package com.example.myApp.landlord.repository;

import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandlordRepository extends JpaRepository<Landlord, Long> {
  boolean existsByTin(String tin);

  Optional<Landlord> findByUser(User user);
}
