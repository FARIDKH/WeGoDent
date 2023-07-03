package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.DoctorDTO;
import com.company.WeGoDent.entity.Doctor;
import com.company.WeGoDent.forms.DoctorUserForm;
import com.company.WeGoDent.mapper.DoctorMapper;
import com.company.WeGoDent.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AdminController {



    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorMapper doctorMapper;

//    @PostMapping("/doctor")
//    @ResponseBody
//    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorUserForm doctorUserForm){
//        Doctor doctor = doctorService.createDoctor(doctorUserForm);
//        return new ResponseEntity<>(doctor, HttpStatus.OK);
//    }


}
