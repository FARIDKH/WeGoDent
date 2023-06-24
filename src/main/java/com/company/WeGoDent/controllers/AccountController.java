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
import com.company.WeGoDent.services.DoctorService;
import com.company.WeGoDent.services.PatientService;
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
    @Autowired

    private  AuthenticationManager authenticationManager;
    @Autowired

    private JwtUtils jwtUtils;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/allUsers")
    public ResponseEntity<SuccessResponse> getAllUser() {
        List<UserDTO> users = userService.getUsers().stream().map(mapper::copyUserEntityToDto).toList();
        return new ResponseEntity<>(new SuccessResponse(users, MessageFormat.format("{0} result found", users.size())), HttpStatus.OK);
    }

    @GetMapping("/account")
    public ResponseEntity<?> getCurrentlyLoggedInUser(Principal principal){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication.getPrincipal());

//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = principal.getName();
        System.out.println(currentUserName);
            User user = userService.getUserByUsername(currentUserName).get();

//            List<GroupRole> roles = user.getRoles();
            List<String> roles = user.getRoles().stream()
                    .map(role -> role.getCode().toString())
                    .collect(Collectors.toList());

        System.out.println(roles);
        if(roles.contains("DOCTOR")){
                return ResponseEntity.ok(doctorService.getDoctorByUserId(user.getId()));
            } else if(roles.contains("PATIENT")){
                return ResponseEntity.ok(patientService.findByUserId(user.getId()));
            }
//        }
        System.out.println("anonym");
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(@Valid @RequestBody SignupDTO userDTO) {
        User user = userService.createUserFromDTO(userDTO);
        String encodePassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodePassword);
        User newUser = userService.save(user);
        return ResponseEntity.ok(new SuccessResponse(mapper.copyUserEntityToDto(newUser), "Registered successfully"));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));
        System.out.println("auth saved : " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }





}
