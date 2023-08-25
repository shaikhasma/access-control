package com.gotech.accesscontrol.repository;

import com.gotech.accesscontrol.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {}
