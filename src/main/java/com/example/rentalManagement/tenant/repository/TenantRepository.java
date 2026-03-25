package com.example.rentalManagement.tenant.repository;

import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.tenant.entity.Tenant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

  Optional<Tenant> findByUserId(Long userId);

  List<Tenant> findAllByLandlord(Landlord landlord);

  boolean existsByEmail(String email);

  Optional<Tenant> findByEmail(String email);

  Optional<Tenant> findByEmailAndUserIsNull(String email);

  @Query(
      "SELECT t FROM Tenant t WHERE t.landlord = :landlord AND ("
          + "LOWER(CONCAT(t.name, ' ', t.surname)) LIKE LOWER(CONCAT('%', :query, '%')) OR "
          + "LOWER(CONCAT(t.surname, ' ', t.name)) LIKE LOWER(CONCAT('%', :query, '%')))")
  List<Tenant> searchTenantsByNameAndSurname(
      @Param("query") String query, @Param("landlord") Landlord landlord);

  @Query(
          "SELECT t FROM Tenant t WHERE t.landlord = :landlord AND t.user IS NOT NULL AND "
                  + "LOWER(t.email) LIKE LOWER(CONCAT('%', :query, '%'))")
  List<Tenant> searchTenantsByEmail(
          @Param("query") String query, @Param("landlord") Landlord landlord);

}
