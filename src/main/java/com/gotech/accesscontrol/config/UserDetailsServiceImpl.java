package com.gotech.accesscontrol.config;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     User user=   userRepo.getUserByUserName(username);

     if (user==null)
     {
         throw new UsernameNotFoundException("Could not found user !!");
     }

     CustomUserDetails customUserDetails= new CustomUserDetails(user);
        return customUserDetails;
    }


}
