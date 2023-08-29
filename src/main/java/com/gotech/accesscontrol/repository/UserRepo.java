package com.gotech.accesscontrol.repository;

import com.gotech.accesscontrol.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(
            "SELECT user FROM User user WHERE(user.emailId =:emailId)"
                    + "or (user.mobileNumber =:mobileNumber)")
    List<User> findUserByEmailAndMobileNumber(String emailId, String mobileNumber);
}
