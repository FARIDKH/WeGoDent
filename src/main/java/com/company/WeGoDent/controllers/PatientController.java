package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.AppointmentDTO;
import com.company.WeGoDent.dto.PatientDTO;
import com.company.WeGoDent.dto.PatientPlanDTO;
import com.company.WeGoDent.entity.PatientPlan;
import com.company.WeGoDent.forms.PatientUserForm;
import com.company.WeGoDent.entity.Appointment;
import com.company.WeGoDent.entity.Patient;
import com.company.WeGoDent.mapper.AppointmentMapper;
import com.company.WeGoDent.mapper.PatientMapper;
import com.company.WeGoDent.mapper.PatientPlanMapper;
import com.company.WeGoDent.services.PatientService;
import org.apache.coyote.Response;
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


    @Autowired
    private PatientPlanMapper patientPlanMapper;

    @Autowired
    private PatientMapper patientMapper;


    @Autowired
    private AppointmentMapper appointmentMapper;

    @ResponseBody
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody  PatientDTO patientUserForm){
        Patient patient = patientService.createPatient(patientUserForm);
        return new ResponseEntity<>(
                patientMapper.toDto(patient)
                , HttpStatus.OK);
    }

    @ResponseBody
    @PatchMapping("/{patientId}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable  Long patientId,@RequestBody PatientDTO patientUserForm){
        Patient patient = patientService.updatePatient(patientId,
                patientMapper.toEntity(patientUserForm));
        return new ResponseEntity<>(patientMapper.toDto(patient), HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/{patientId}")
    public ResponseEntity<Boolean> deletePatient(@PathVariable Long patientId){
        boolean l = patientService.deletePatient(patientId);
        if(l){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }

        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long patientId){
        return new ResponseEntity<>(
                patientMapper.toDto(patientService.findById(patientId)),
                HttpStatus.OK
        );
    }

    @ResponseBody
    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<List<AppointmentDTO>> listAppointments(@PathVariable Long patientId){
        return new ResponseEntity<>(
                appointmentMapper.toDto(patientService.listAppointments(patientId)),
                HttpStatus.OK
        );
    }

    @ResponseBody
    @GetMapping("/{patientId}/plan")
    public ResponseEntity<List<PatientPlanDTO>> getPatientPlans(@PathVariable Long patientId){

        return ResponseEntity.ok(
                patientPlanMapper.toDto(patientService.listPlans(patientId))
        );

    }


}
