package com.company.WeGoDent.dto;

import com.company.WeGoDent.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

//    private String firstName;
//    private String lastName;
//    private String email;
//    private String phoneNumber;
//
//    private String password;
    private UserDTO userDTO;
    private LocalDateTime dateOfBirth;
    private String allergicReactions;
    private String specificIllness;


//    private UserType userType = UserType.ROLE_PATIENT;



}
