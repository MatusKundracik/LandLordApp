package com.example.rentalManagement.item.repository;

import com.example.rentalManagement.apartment.entity.Apartment;
import com.example.rentalManagement.item.entity.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

  List<Item> findAllByApartment(Apartment apartment);
}
