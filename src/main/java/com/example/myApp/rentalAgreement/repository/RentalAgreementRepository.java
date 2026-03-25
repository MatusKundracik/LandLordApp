package com.example.myApp.rentalAgreement.repository;

import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.rentalAgreement.entity.RentalAgreement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Long> {

  List<RentalAgreement> getAllRentalAgreementsByLandlord(Landlord landlord);
}
