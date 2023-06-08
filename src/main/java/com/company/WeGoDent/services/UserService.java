package com.company.WeGoDent.services;


import com.company.WeGoDent.forms.DoctorUserForm;
import com.company.WeGoDent.forms.UserForm;
import com.company.WeGoDent.models.Doctor;
import com.company.WeGoDent.models.User;
import com.company.WeGoDent.models.UserType;
import com.company.WeGoDent.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createDoctorUser(DoctorUserForm userForm){
        User user = new User();
        user.setEmail(userForm.email);
        user.setUserRole(UserType.DOCTOR);
        user.setLastName(userForm.lastName);
        user.setFirstName(userForm.firstName);
        user.setPassword(this.generateRandomPassword());
        user.setPhoneNumber(userForm.phoneNumber);

        return userRepository.save(user);
    }

    public User createPatientUser(UserForm userForm){
        User user = new User();
        user.setEmail(userForm.email);
        user.setUserRole(UserType.PATIENT);
        user.setLastName(userForm.lastName);
        user.setFirstName(userForm.firstName);
        user.setPassword(this.generateRandomPassword());
        user.setPhoneNumber(userForm.phoneNumber);

        return userRepository.save(user);
    }

    public User createBloggerUser(UserForm userForm){
        User user = new User();
        user.setEmail(userForm.email);
        user.setUserRole(UserType.BLOGGER);
        user.setLastName(userForm.lastName);
        user.setFirstName(userForm.firstName);
        user.setPassword(this.generateRandomPassword());
        user.setPhoneNumber(userForm.phoneNumber);

        return userRepository.save(user);
    }

    public User createAdminUser(UserForm userForm){
        User user = new User();
        user.setEmail(userForm.email);
        user.setUserRole(UserType.ADMIN);
        user.setLastName(userForm.lastName);
        user.setFirstName(userForm.firstName);
        user.setPassword(this.generateRandomPassword());
        user.setPhoneNumber(userForm.phoneNumber);

        return userRepository.save(user);
    }


    private String generateRandomPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode("random-password");
    }

}
