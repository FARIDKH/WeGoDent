package com.company.WeGoDent;

import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.entity.User;
import com.company.WeGoDent.enums.UserType;
import com.company.WeGoDent.repositories.GroupRoleRepository;
import com.company.WeGoDent.security.services.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AdminUserInitializer implements CommandLineRunner {


    @Autowired
    private UserService userService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GroupRoleRepository groupRoleRepository;





    @Override
    public void run(String... args) throws Exception {

        if (!userService.getUserByUsername("admin").isPresent()) {
            // Create a new admin user
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("adminPassword")); // You can set your desired password here
            GroupRole doctorRole = groupRoleRepository.findByCode(UserType.ROLE_ADMIN);
            doctorRole = groupRoleRepository.save(doctorRole);
            List<GroupRole> groupRoleList = new ArrayList<>();
            groupRoleList.add(doctorRole);
            adminUser.setRoles(groupRoleList);
            System.out.println(doctorRole);


            userService.save(adminUser);

            System.out.println("Admin user created successfully.");
        } else {
            System.out.println("Admin user already exists.");
        }


    }



}
