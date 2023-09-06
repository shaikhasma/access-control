package com.gotech.accesscontrol.repository;

import com.gotech.accesscontrol.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(
            "SELECT user FROM User user WHERE(user.emailId =:emailId)"
                    + "or (user.mobileNumber =:mobileNumber)")
    List<User> findUserByEmailAndMobileNumber(String emailId, String mobileNumber);


    @Query(value = "SELECT * FROM txn_user u WHERE (u.email_id = :identifier OR u.mobile_number = :identifier) AND u.password = :password", nativeQuery = true)
    User findByEmailOrMobileNumberAndPassword(@Param("identifier") String identifier, @Param("password") String password);



}
