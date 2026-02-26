package com.example.myApp.landlord.entity;

import com.example.myApp.shared.AuditableEntity;
import com.example.myApp.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "landlords")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Landlord extends AuditableEntity {

  private String name;
  private String surname;
  private LocalDate dateOfBirth;
  private String street;
  private String city;
  private String postalCode;
  private String country;
  private String tin;
  private String phoneNumber;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
