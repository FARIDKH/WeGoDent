package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.AppointmentDTO;
import com.company.WeGoDent.dto.DoctorDTO;
import com.company.WeGoDent.dto.PatientDTO;
import com.company.WeGoDent.entity.Patient;
import com.company.WeGoDent.enums.DoctorType;
import com.company.WeGoDent.forms.DoctorUserForm;
import com.company.WeGoDent.forms.TimeSlotForm;
import com.company.WeGoDent.entity.Appointment;
import com.company.WeGoDent.entity.Doctor;
import com.company.WeGoDent.entity.DoctorAvailability;
import com.company.WeGoDent.mapper.AppointmentMapper;
import com.company.WeGoDent.mapper.DoctorMapper;
import com.company.WeGoDent.mapper.PatientMapper;
import com.company.WeGoDent.services.AppointmentService;
import com.company.WeGoDent.services.DoctorAvailabilityService;
import com.company.WeGoDent.services.DoctorService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentMapper appointmentMapper;



    @GetMapping
    public List<Doctor> retrieveDoctorsByLocationAndType(
            @RequestParam("doctorType") DoctorType doctorType,
            @RequestParam("officeLocation") String officeLocation) {

        return doctorService.retrieveDoctorsByLocationAndType(doctorType, officeLocation);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorMapper.toDto(
                doctorService.findById(doctorId)
        ));
    }



    @PutMapping("/appointment/{appointmentId}/treatment-phase/{treatmentPhaseId}")
    @ResponseBody
    public ResponseEntity<Appointment> acceptAppointment(@PathVariable Long appointmentId,
                                                         @PathVariable Long treatmentPhaseId){
        return ResponseEntity.ok(
                appointmentService.acceptAppointment(appointmentId,treatmentPhaseId)
        );
    }

    @PutMapping("/treatment-phase/{treatmentPhaseId}")
    @ResponseBody
    public ResponseEntity<AppointmentDTO> scheduleAppointment(@RequestBody AppointmentDTO appointmentDTO,
                                                              @PathVariable Long treatmentPhaseId){


        Appointment appointment = appointmentService.scheduleAppointment(appointmentMapper.toEntity(appointmentDTO),treatmentPhaseId);
        return ResponseEntity.ok(
                appointmentMapper.toDto(appointment)
        );

    }

    @ResponseBody
    @PutMapping("/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long doctorId, @RequestBody DoctorUserForm doctorUserForm){
        Doctor doctor = doctorService.updateDoctor(doctorId,doctorUserForm);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable Long doctorId){
        boolean l = doctorService.deleteDoctor(doctorId);
        if(l){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }

        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable Long doctorId){
        List<Appointment> appointments = doctorService.listAppointments(doctorId);
        return new ResponseEntity<>(appointments,HttpStatus.OK);
    }

    @PostMapping("/{doctorId}/availability")
    @ResponseBody
    public ResponseEntity<Boolean> createAvailability(@PathVariable Long doctorId,
                                                      @RequestBody TimeSlotForm timeSlotForm){
        doctorAvailabilityService.createAvailability(timeSlotForm);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @PutMapping("/{doctorId}/availability")
    @ResponseBody
    public ResponseEntity<Boolean> updateAvailability(@PathVariable Long doctorId,
                                                      @RequestBody TimeSlotForm timeSlotForm){
        doctorAvailabilityService.createAvailability(timeSlotForm);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}/availability")
    @ResponseBody
    public ResponseEntity<Boolean> deleteAvailability(@PathVariable Long doctorId,
                                                      @RequestBody TimeSlotForm timeSlotForm){
        doctorAvailabilityService.deleteAvailability(timeSlotForm);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @GetMapping("/{doctorId}/availability")
    @ResponseBody
    public ResponseEntity<List<DoctorAvailability>> listAvailability(@PathVariable Long doctorId){
        return new ResponseEntity<>(doctorAvailabilityService.getAvailabilities(doctorId),HttpStatus.OK);
    }

    @GetMapping("/{doctorId}/rating")
    @ResponseBody
    public ResponseEntity<Double> getDoctorRating(@PathVariable Long doctorId){
        return new ResponseEntity<>(doctorService.getDoctorRating(doctorId),HttpStatus.OK);
    }

    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<PatientDTO>> listPatients(@PathVariable Long doctorId){
        List<Patient> patients = doctorService.getPatientList(doctorId);

        return ResponseEntity.ok(
                patientMapper.toDto(patients)
        );
    }



}
