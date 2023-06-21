package com.company.WeGoDent.controllers;


import com.company.WeGoDent.enums.DoctorType;
import com.company.WeGoDent.forms.DoctorUserForm;
import com.company.WeGoDent.forms.TimeSlotForm;
import com.company.WeGoDent.entity.Appointment;
import com.company.WeGoDent.entity.Doctor;
import com.company.WeGoDent.entity.DoctorAvailability;
import com.company.WeGoDent.services.DoctorAvailabilityService;
import com.company.WeGoDent.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorAvailabilityService doctorAvailabilityService;


    @GetMapping
    public List<Doctor> retrieveDoctorsByLocationAndType(
            @RequestParam("doctorType") DoctorType doctorType,
            @RequestParam("officeLocation") String officeLocation) {

        return doctorService.retrieveDoctorsByLocationAndType(doctorType, officeLocation);
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorUserForm doctorUserForm){
        Doctor doctor = doctorService.createDoctor(doctorUserForm);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/update/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody DoctorUserForm doctorUserForm){
        Doctor doctor = doctorService.updateDoctor(id,doctorUserForm);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable Long id){
        boolean l = doctorService.deleteDoctor(id);
        if(l){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }

        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable Long id){
        List<Appointment> appointments = doctorService.listAppointments(id);
        return new ResponseEntity<>(appointments,HttpStatus.OK);
    }

    @PostMapping("/{id}/availability")
    @ResponseBody
    public ResponseEntity<Boolean> createAvailability(@PathVariable Long id,
                                                      @RequestBody TimeSlotForm timeSlotForm){
        doctorAvailabilityService.createAvailability(timeSlotForm);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @PutMapping("/{id}/availability")
    @ResponseBody
    public ResponseEntity<Boolean> updateAvailability(@PathVariable Long id,
                                                      @RequestBody TimeSlotForm timeSlotForm){
        doctorAvailabilityService.createAvailability(timeSlotForm);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @DeleteMapping("/{id}/availability")
    @ResponseBody
    public ResponseEntity<Boolean> deleteAvailability(@PathVariable Long id,
                                                      @RequestBody TimeSlotForm timeSlotForm){
        doctorAvailabilityService.deleteAvailability(timeSlotForm);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @GetMapping("/{id}/availability")
    @ResponseBody
    public ResponseEntity<List<DoctorAvailability>> listAvailability(@PathVariable Long id){
        return new ResponseEntity<>(doctorAvailabilityService.getAvailabilities(id),HttpStatus.OK);
    }

    @GetMapping("/{id}/rating")
    @ResponseBody
    public ResponseEntity<Double> getDoctorRating(@PathVariable Long id){



        return new ResponseEntity<>(doctorService.getDoctorRating(id),HttpStatus.OK);
    }

}
