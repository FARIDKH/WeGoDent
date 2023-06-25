package com.company.WeGoDent.controllers;


import com.company.WeGoDent.entity.Doctor;
import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.records.JwtResponse;
import com.company.WeGoDent.records.LoginDTO;
import com.company.WeGoDent.dto.SignupDTO;
import com.company.WeGoDent.records.SuccessResponse;
import com.company.WeGoDent.dto.UserDTO;
import com.company.WeGoDent.entity.User;
import com.company.WeGoDent.mapper.UserMapper;
import com.company.WeGoDent.security.JwtUtils;
import com.company.WeGoDent.security.services.UserService;
import com.company.WeGoDent.services.AccountService;
import com.company.WeGoDent.services.DoctorService;
import com.company.WeGoDent.services.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {


    @Autowired
    private UserService userService;
    @Autowired

    private  PasswordEncoder passwordEncoder;
//    @Autowired
//
//    private  AuthenticationManager authenticationManager;
//    @Autowired
//
//    private JwtUtils jwtUtils;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/allUsers")
    public ResponseEntity<SuccessResponse> getAllUser() {
        List<UserDTO> users = userService.getUsers().stream().map(mapper::toDto).toList();
        return new ResponseEntity<>(new SuccessResponse(users, MessageFormat.format("{0} result found", users.size())), HttpStatus.OK);
    }

    @GetMapping("/account")
    public ResponseEntity<?> getCurrentlyLoggedInUser(Principal principal){

        String currentUserName = principal.getName();
        User user = userService.getUserByUsername(currentUserName).get();

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getCode().toString())
                .collect(Collectors.toList());

        if(roles.contains("ROLE_DOCTOR")){
            return ResponseEntity.ok(doctorService.getDoctorByUserId(user.getId()));
        } else if(roles.contains("ROLE_PATIENT")){
            return ResponseEntity.ok(patientService.findByUserId(user.getId()));
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(@Valid @RequestBody SignupDTO userDTO) {
        User user = userService.createUserFromDTO(userDTO);
        String encodePassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodePassword);
        User newUser = userService.save(user);
        return ResponseEntity.ok(new SuccessResponse(mapper.toDto(newUser), "Registered successfully"));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {

        return accountService.authenticate(loginDTO);

    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().invalidate();


        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("Logged out successfully.");
    }





}
