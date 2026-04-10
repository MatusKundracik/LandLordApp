package com.example.rentalManagement.rentalAgreement.repository;

import com.example.rentalManagement.apartment.entity.Apartment;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.rentalAgreement.entity.ContractStatus;
import com.example.rentalManagement.rentalAgreement.entity.RentalAgreement;
import com.example.rentalManagement.tenant.entity.Tenant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Long> {

  List<RentalAgreement> getAllRentalAgreementsByLandlord(Landlord landlord);

  List<RentalAgreement> getAllRentalAgreementsByTenant(Tenant tenant);

  List<RentalAgreement> findByTenantAndStatus(Tenant tenant, ContractStatus status);

  Optional<RentalAgreement> findByTenantAndApartmentAndStatus(
      Tenant tenant, Apartment apartment, ContractStatus status);

  @Query(
      """
      SELECT COUNT(ra) > 0 FROM RentalAgreement ra
      WHERE ra.apartment = :apartment
        AND ra.status = 'ACTIVE'
        AND (:newEnd IS NULL OR ra.startDate <= :newEnd)
        AND (ra.endDate IS NULL OR ra.endDate >= :newStart)
        AND (:excludeId IS NULL OR ra.id != :excludeId)
      """)
  boolean existsOverlappingAgreement(
      @Param("apartment") Apartment apartment,
      @Param("newStart") LocalDate newStart,
      @Param("newEnd") LocalDate newEnd,
      @Param("excludeId") Long excludeId);
}
