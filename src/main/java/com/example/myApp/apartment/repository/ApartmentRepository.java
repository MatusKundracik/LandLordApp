package com.example.myApp.apartment.repository;

import com.example.myApp.apartment.entity.Apartment;
import com.example.myApp.landlord.entity.Landlord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

  List<Apartment> getAllApartmentsByLandlord(Landlord landlord);
}
