package com.example.rentalManagement.tenant.repository;

import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.tenant.entity.Tenant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

  Optional<Tenant> findByUserId(Long userId);

  List<Tenant> findAllByLandlord(Landlord landlord);

  boolean existsByEmail(String email);

    Optional<Tenant> findByEmail(String email);

    Optional<Tenant> findByEmailAndUserIsNull(String email);

}
