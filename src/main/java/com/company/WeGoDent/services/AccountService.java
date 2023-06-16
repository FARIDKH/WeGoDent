package com.company.WeGoDent.services;


import com.company.WeGoDent.forms.DoctorUserForm;
import com.company.WeGoDent.forms.PatientUserForm;
import com.company.WeGoDent.forms.UserForm;
import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.entity.User;
import com.company.WeGoDent.enums.UserType;
import com.company.WeGoDent.repositories.GroupRoleRepository;
import com.company.WeGoDent.repositories.UserRepository;
import com.company.WeGoDent.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private GroupRoleRepository groupRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User getUserById(Long id){
        if(userRepository.existsById(id)){
            return userRepository.getReferenceById(id);
        }
        return null;
    }

    public User createDoctorUser(DoctorUserForm userForm){
        User user = new User();
        user.setEmail(userForm.email);


        GroupRole doctorRole = groupRoleRepository.findByCode(UserType.DOCTOR);
        List<GroupRole> groupRoleList = new ArrayList<>();
        groupRoleList.add(doctorRole);
        user.setRoles(groupRoleList);
        System.out.println(doctorRole);
        String pwd = "changeYourPasswordDoctor2002!";


        String username = userForm.firstName + userForm.lastName;
        Random random = new Random();
        int number = random.nextInt(90000000) + 10000000;
        String text = username + number;

        user.setUsername(text);

        user.setPassword(passwordEncoder.encode(pwd));

        user.setLastName(userForm.lastName);
        user.setFirstName(userForm.firstName);
        user.setPhoneNumber(userForm.phoneNumber);

//        return userRepository.save(user);
        return userService.save(user);
    }


    public User createPatientUser(PatientUserForm userForm){
        User user = new User();
        user.setEmail(userForm.email);

        GroupRole patientRole = groupRoleRepository.findByCode(UserType.PATIENT);
        List<GroupRole> groupRoleList = new ArrayList<>();
        groupRoleList.add(patientRole);
        user.setRoles(groupRoleList);

        String pwd = "changeYourPasswordPatient2002!";

        String username = userForm.firstName + userForm.lastName;
        Random random = new Random();
        int number = random.nextInt(90000000) + 10000000;
        String text = username + number;



        user.setUsername(text);

        user.setPassword(passwordEncoder.encode(pwd));

        user.setLastName(userForm.lastName);
        user.setFirstName(userForm.firstName);
        user.setPhoneNumber(userForm.phoneNumber);

        return userService.save(user);
    }




    public User createBloggerUser(User userForm){
        User user = new User();
        user.setEmail(userForm.getEmail());

        GroupRole patientRole = groupRoleRepository.findByCode(UserType.BLOGGER);
        List<GroupRole> groupRoleList = new ArrayList<>();
        groupRoleList.add(patientRole);
        user.setRoles(groupRoleList);

        String pwd = "changeYourPasswordBlogger2002!";

        String username = userForm.getFirstName() + userForm.getLastName();
        Random random = new Random();
        int number = random.nextInt(90000000) + 10000000;
        String text = username + number;



        user.setUsername(text);

        user.setPassword(passwordEncoder.encode(pwd));

        user.setLastName(userForm.getFirstName());
        user.setFirstName(userForm.getLastName());
        user.setPhoneNumber(userForm.getPhoneNumber());

        return userService.save(user);
    }

    public User createAdminUser(UserForm userForm){
        User user = new User();
        user.setEmail(userForm.email);

        GroupRole patientRole = groupRoleRepository.findByCode(UserType.ADMIN);
        List<GroupRole> groupRoleList = new ArrayList<>();
        groupRoleList.add(patientRole);
        user.setRoles(groupRoleList);

        String pwd = "changeYourPasswordADMIN2002!";

        String username = userForm.firstName + userForm.lastName;
        Random random = new Random();
        int number = random.nextInt(90000000) + 10000000;
        String text = username + number;



        user.setUsername(text);

        user.setPassword(passwordEncoder.encode(pwd));

        user.setLastName(userForm.lastName);
        user.setFirstName(userForm.firstName);
        user.setPhoneNumber(userForm.phoneNumber);

        return userService.save(user);
    }



    public User updateUser(Long id,UserForm userForm){
        if(userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            user.setPhoneNumber(userForm.phoneNumber);
            user.setPassword(userForm.password);
            user.setEmail(userForm.email);
            user.setFirstName(userForm.firstName);
            user.setLastName(userForm.lastName);

            return user;
        }
        return null;
    }


    public Boolean deleteUser(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private String generateRandomPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode("random-password");
    }

}
