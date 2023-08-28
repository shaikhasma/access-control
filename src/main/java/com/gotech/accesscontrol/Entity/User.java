package com.gotech.accesscontrol.Entity;

import com.gotech.accesscontrol.constant.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "txn_user",schema = "gts_profile")
public class User {
  @Id
  @GeneratedValue(generator="uuid2")  // Universally unique identifier generation
  @GenericGenerator(name="uuid2", strategy = "uuid2")
  @Column (name = "user_id", columnDefinition = "VARCHAR(36)")
  private String userId;

  @Column(name = "first_name",nullable = false)
  private String firstName;

  @Column(name = "last_name",nullable = false)
  private String lastName;

  @Column(name = "mobile_number", unique = true,nullable = false)
  private String mobileNumber;

  @Column(unique = true,nullable = false)
  private String emailId;

  @Column(nullable = false)
  private String password;

  @Column(name = "user_type",nullable = false)
  @Enumerated(EnumType.STRING)
  private UserType userType;
}
