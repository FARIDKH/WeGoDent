package com.company.WeGoDent.forms;

import com.company.WeGoDent.enums.UserType;

public class PatientUserForm {

    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;

    public String password;
    public UserType userType = UserType.ROLE_PATIENT;


}
