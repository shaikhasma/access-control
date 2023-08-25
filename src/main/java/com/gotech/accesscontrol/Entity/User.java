package com.gotech.accesscontrol.Entity;

import com.gotech.accesscontrol.constant.UserType;
import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_registration")
public class User {
  @Id
  @SequenceGenerator(
      name = "id_sequence",
      sequenceName = "id_sequence",
      initialValue = 1,
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id_generator")
  private int id;

  @NotNull
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Column(name = "last_name")
  private String lastName;

  @NotNull
  @Column(name = "mobile_number", unique = true)
  private String mobileNumber;

  @NotNull
  @Column(unique = true)
  private String emailId;

  @NotNull @Column private String password;

  @NotNull
  @Column(name = "user_type")
  @Enumerated(EnumType.STRING)
  private UserType userType;
}
