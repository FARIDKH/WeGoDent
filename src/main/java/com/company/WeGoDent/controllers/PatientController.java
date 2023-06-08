package com.company.WeGoDent.controllers;


import com.company.WeGoDent.forms.PatientUserForm;
import com.company.WeGoDent.forms.UserForm;
import com.company.WeGoDent.models.Appointment;
import com.company.WeGoDent.models.Patient;
import com.company.WeGoDent.models.User;
import com.company.WeGoDent.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @ResponseBody
    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@RequestBody  PatientUserForm patientUserForm){
        Patient patient = patientService.createPatient(patientUserForm);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable  Long id,@RequestBody PatientUserForm patientUserForm){
        Patient patient = patientService.updatePatient(id,patientUserForm);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePatient(@PathVariable Long id){
        boolean l = patientService.deletePatient(id);
        if(l){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }

        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointment>> listAppointments(@PathVariable Long id){
        return new ResponseEntity<>(
                patientService.listAppointments(id),
                HttpStatus.OK
        );
    }

}
