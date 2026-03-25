package com.example.myApp.landlord.repository;

import com.example.myApp.landlord.entity.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandlordRepository extends JpaRepository<Landlord, Long> {

}
