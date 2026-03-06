package com.example.myApp.item.repository;

import com.example.myApp.apartment.entity.Apartment;
import com.example.myApp.item.entity.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

  List<Item> findAllByApartment(Apartment apartment);
}
