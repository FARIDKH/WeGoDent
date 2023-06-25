package com.company.WeGoDent.forms;

import com.company.WeGoDent.enums.DoctorType;
import com.company.WeGoDent.enums.UserType;
import org.locationtech.jts.geom.Point;

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
    public UserType userType = UserType.ROLE_DOCTOR;



}
