package com.example.myApp.apartment.repository;

import com.example.myApp.apartment.dtos.ApartmentResponseDto;
import com.example.myApp.apartment.entity.Apartment;
import com.example.myApp.landlord.entity.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    List<Apartment> getAllApartmentsByLandlord(Landlord landlord);

}
