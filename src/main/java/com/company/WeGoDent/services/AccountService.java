package com.company.WeGoDent.services;


import com.company.WeGoDent.dto.UserDTO;
import com.company.WeGoDent.entity.Token;
import com.company.WeGoDent.enums.TokenType;
import com.company.WeGoDent.forms.DoctorUserForm;
import com.company.WeGoDent.forms.PatientUserForm;
import com.company.WeGoDent.forms.UserForm;
import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.entity.User;
import com.company.WeGoDent.enums.UserType;
import com.company.WeGoDent.records.AuthenticationResponse;
import com.company.WeGoDent.records.JwtResponse;
import com.company.WeGoDent.records.LoginDTO;
import com.company.WeGoDent.repositories.GroupRoleRepository;
import com.company.WeGoDent.repositories.TokenRepository;
import com.company.WeGoDent.repositories.UserRepository;
import com.company.WeGoDent.security.JwtUtils;
import com.company.WeGoDent.security.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    @Autowired
    private TokenRepository tokenRepository;


    @Autowired

    private AuthenticationManager authenticationManager;
    @Autowired

    private JwtUtils jwtUtils;



    public User getUserById(Long id){
        if(userRepository.existsById(id)){
            return userRepository.getReferenceById(id);
        }
        return null;
    }

    public User createDoctorUser(User userForm){
        User user = new User();
        user.setEmail(userForm.getEmail());


        GroupRole doctorRole = groupRoleRepository.findByCode(UserType.ROLE_DOCTOR);
        List<GroupRole> groupRoleList = new ArrayList<>();
        groupRoleList.add(doctorRole);
        user.setRoles(groupRoleList);;
        String pwd = "changeYourPasswordDoctor2002!";


        String username = userForm.getFirstName() + userForm.getLastName();
        Random random = new Random();
        int number = random.nextInt(90000000) + 10000000;
        String text = username + number;

        user.setUsername(text);

        user.setPassword(passwordEncoder.encode(pwd));

        user.setLastName(userForm.getLastName());
        user.setFirstName(userForm.getFirstName());
        user.setPhoneNumber(userForm.getPhoneNumber());

        return userService.save(user);
    }


    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtUtils.getUserNameFromJwtToken(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtUtils.validateJwtToken(refreshToken)) {
                var accessToken = jwtUtils.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


    public ResponseEntity<JwtResponse> authenticate(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        User userDetails = (User) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        revokeAllUserTokens(userDetails);
        saveUserToken(userDetails,jwt);


        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    public User createPatientUser(UserDTO userForm){
        User user = new User();
        user.setEmail(userForm.getEmail());

        GroupRole patientRole = groupRoleRepository.findByCode(UserType.ROLE_PATIENT);
        List<GroupRole> groupRoleList = new ArrayList<>();
        groupRoleList.add(patientRole);
        user.setRoles(groupRoleList);

        String pwd = "changeYourPasswordPatient2002!";

        String username = userForm.getFirstName() + userForm.getLastName();
        Random random = new Random();
        int number = random.nextInt(90000000) + 10000000;
        String text = username + number;



        user.setUsername(text);

        user.setPassword(passwordEncoder.encode(pwd));

        user.setLastName(userForm.getFirstName());
        user.setFirstName(userForm.getLastName());
        user.setPhoneNumber(userForm.getPhoneNumber());

        User createdUser =  userService.save(user);
        var jwtToken = jwtUtils.generateToken(createdUser);
        saveUserToken(user,jwtToken);

        return createdUser;
    }




    public User createBloggerUser(User userForm){
        User user = new User();
        user.setEmail(userForm.getEmail());

        GroupRole patientRole = groupRoleRepository.findByCode(UserType.ROLE_BLOGGER);
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

        GroupRole patientRole = groupRoleRepository.findByCode(UserType.ROLE_ADMIN);
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



    public User updateUser(Long id,User userForm){
        if(userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            user.setPhoneNumber(userForm.getPhoneNumber());
            user.setPassword(userForm.getPassword());
            user.setEmail(userForm.getEmail());
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());

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
