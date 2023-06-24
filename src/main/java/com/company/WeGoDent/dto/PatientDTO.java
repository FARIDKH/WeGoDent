package com.company.WeGoDent.dto;

import com.company.WeGoDent.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private String password;
    private UserType userType = UserType.PATIENT;



}