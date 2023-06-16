package com.company.WeGoDent.security.services;

import com.company.WeGoDent.dto.UserDTO;
import com.company.WeGoDent.entity.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService, UserDetailsPasswordService {


    List<User> getUsers();

    User save(User user);
    User createUserFromDTO(UserDTO dto);

}
