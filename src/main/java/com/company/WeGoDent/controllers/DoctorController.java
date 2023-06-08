package com.company.WeGoDent.controllers;


import com.company.WeGoDent.forms.DoctorUserForm;
import com.company.WeGoDent.forms.UserForm;
import com.company.WeGoDent.models.Doctor;
import com.company.WeGoDent.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private static DoctorService doctorService;

    @PostMapping("/create")
    public Doctor createDoctor(@RequestBody DoctorUserForm doctorUserForm){
        return doctorService.createDoctor(doctorUserForm);
    }

}
