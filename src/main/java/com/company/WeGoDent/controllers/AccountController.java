package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.LoginDTO;
import com.company.WeGoDent.dto.SignupDTO;
import com.company.WeGoDent.dto.SuccessResponse;
import com.company.WeGoDent.dto.UserDTO;
import com.company.WeGoDent.mapper.AppMapper;
import com.company.WeGoDent.security.TokenProvider;
import com.company.WeGoDent.security.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {


    @Autowired
    private UserService userService;
    @Autowired

    private  PasswordEncoder passwordEncoder;
    @Autowired

    private  AuthenticationManager authenticationManager;
    @Autowired


    private  TokenProvider tokenProvider;

    @Autowired
    private  AppMapper mapper;

    @GetMapping("/user")
    public ResponseEntity<SuccessResponse> getAllUser() {
        List<UserDTO> users = userService.getUsers().stream().map(mapper::copyUserEntityToDto).toList();
        return new ResponseEntity<>(new SuccessResponse(users, MessageFormat.format("{0} result found", users.size())), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(@Valid @RequestBody SignupDTO userDTO) {
        var user = mapper.copyUserDtoToEntity(userDTO);
        var encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        var newUser = userService.save(user);

        return ResponseEntity.ok(new SuccessResponse(mapper.copyUserEntityToDto(newUser), "register Successfully"));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<SuccessResponse> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new SuccessResponse(jwt, "Login Successfully"));
    }





}
