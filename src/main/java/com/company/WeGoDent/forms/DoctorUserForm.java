package com.company.WeGoDent.forms;

import com.company.WeGoDent.models.DoctorType;
import com.company.WeGoDent.models.UserType;

public class DoctorUserForm {

    public String biography;
    public String experience;
    public Double hourly_rate;
    public String language;
    public DoctorType doctorType;
    public String office_location;

    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String password;
    public UserType userType = UserType.DOCTOR;



}
