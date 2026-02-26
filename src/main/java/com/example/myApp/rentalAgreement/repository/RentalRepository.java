package com.example.myApp.rentalAgreement.repository;

import com.example.myApp.rentalAgreement.entity.RentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalAgreement,Long> {
}
