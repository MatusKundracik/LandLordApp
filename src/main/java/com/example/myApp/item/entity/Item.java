package com.example.myApp.item.entity;

import com.example.myApp.apartment.entity.Apartment;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name; // "Práčka", "Posteľ"...
  private String description; // "Nový", "Opotrebovaný"
  private Integer quantity;
  private BigDecimal value; // Hodnota pre odpočet z depozitu

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "apartment_id", nullable = false)
  private Apartment apartment; // @ManyToOne
}
