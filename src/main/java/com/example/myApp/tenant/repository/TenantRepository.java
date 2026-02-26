package com.example.myApp.tenant.repository;

import com.example.myApp.tenant.entity.Tenant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

  Optional<Tenant> findByUserId(Long userId);
}
