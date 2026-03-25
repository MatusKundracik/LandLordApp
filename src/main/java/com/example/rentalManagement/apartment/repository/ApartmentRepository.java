package com.example.rentalManagement.apartment.repository;

import com.example.rentalManagement.apartment.entity.Apartment;
import com.example.rentalManagement.landlord.entity.Landlord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

  List<Apartment> getAllApartmentsByLandlord(Landlord landlord);
}
