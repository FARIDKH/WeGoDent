package com.company.WeGoDent.security.services;

import com.company.WeGoDent.models.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService, UserDetailsPasswordService {


    List<User> getUsers();

    User save(User user);

}
