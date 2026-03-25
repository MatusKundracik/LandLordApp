package com.example.myApp.rentalAgreement.repository;

import com.example.myApp.apartment.entity.Apartment;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.rentalAgreement.entity.ContractStatus;
import com.example.myApp.rentalAgreement.entity.RentalAgreement;
import java.util.List;
import java.util.Optional;

import com.example.myApp.tenant.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Long> {

  List<RentalAgreement> getAllRentalAgreementsByLandlord(Landlord landlord);

  List<RentalAgreement> getAllRentalAgreementsByTenant(Tenant tenant);

  List<RentalAgreement> findByTenantAndStatus(Tenant tenant, ContractStatus status);

    Optional<RentalAgreement> findByTenantAndApartmentAndStatus(
            Tenant tenant, Apartment apartment, ContractStatus status);


}
