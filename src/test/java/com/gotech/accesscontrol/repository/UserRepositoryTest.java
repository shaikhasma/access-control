package com.gotech.accesscontrol.repository;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.constant.enums.UserType;
import com.gotech.accesscontrol.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepo userRepo;
    private User user;

    public void setUp() {
        user = User.builder()
                .firstName("Vinay")
                .lastName("Shinde")
                .mobileNumber("8847592130")
                .emailId("vs@gmail.com")
                .password("vs@1234")
                .userType(UserType.BRANCH_MANAGER)
                .build();
    }

    public void tearDown() {
        user = null;
    }

    @Test
    public void testSaveUser() {
        this.setUp();
        User savedUser = userRepo.save(user);
        assertThat(savedUser).isNotNull();
        assertEquals(savedUser.getEmailId(), user.getEmailId());
        assertEquals(savedUser.getMobileNumber(), user.getMobileNumber());
        this.tearDown();
    }

    @Test
    public void testUserAlreadyPresentInDatabase() {
        this.setUp();
        User savedUser = userRepo.save(user);
        User newUser = user;
        List<User> duplicateUsers = userRepo.findUserByEmailAndMobileNumber(newUser.getEmailId(), newUser.getMobileNumber());
        assertThat(duplicateUsers).isNotEmpty();
        assertEquals(savedUser.getMobileNumber(), newUser.getMobileNumber());
        assertEquals(savedUser.getEmailId(), newUser.getEmailId());
        this.tearDown();
    }

    @Test
    public void testUserNotAlreadyPresentInDatabase() {
        this.setUp();
        User savedUser = userRepo.save(user);
        User newUser = User.builder()
                .firstName("Vijay")
                .lastName("Powar")
                .emailId("vpowar@gmail.com")
                .mobileNumber("74859601102")
                .password("vpowar@44557744")
                .userType(UserType.SALES_EXECUTIVE)
                .build();
        List<User> duplicateUsers = userRepo.findUserByEmailAndMobileNumber(newUser.getEmailId(), newUser.getMobileNumber());
        assertThat(duplicateUsers).isEmpty();
        assertNotEquals(savedUser.getMobileNumber(), newUser.getMobileNumber());
        assertNotEquals(savedUser.getEmailId(), newUser.getEmailId());
        this.tearDown();
    }
}