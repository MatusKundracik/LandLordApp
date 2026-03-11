package com.example.rentalManagement.rentalAgreement.repository;

import com.example.rentalManagement.apartment.entity.Apartment;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.rentalAgreement.entity.ContractStatus;
import com.example.rentalManagement.rentalAgreement.entity.RentalAgreement;
import com.example.rentalManagement.tenant.entity.Tenant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Long> {

  List<RentalAgreement> getAllRentalAgreementsByLandlord(Landlord landlord);

  List<RentalAgreement> getAllRentalAgreementsByTenant(Tenant tenant);

  List<RentalAgreement> findByTenantAndStatus(Tenant tenant, ContractStatus status);

  Optional<RentalAgreement> findByTenantAndApartmentAndStatus(
      Tenant tenant, Apartment apartment, ContractStatus status);
}
