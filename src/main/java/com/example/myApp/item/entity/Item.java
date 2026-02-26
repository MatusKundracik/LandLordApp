package com.example.myApp.item.entity;

import com.example.myApp.apartment.entity.Apartment;
import com.example.myApp.shared.AuditableEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends AuditableEntity {

  private String name; // "Práčka", "Posteľ"...
  private String description; // "Nový", "Opotrebovaný"
  private Integer quantity;
  private BigDecimal value; // Hodnota pre odpočet z depozitu

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "apartment_id", nullable = false)
  private Apartment apartment; // @ManyToOne
}
