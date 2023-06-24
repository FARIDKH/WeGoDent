package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.AppointmentDTO;
import com.company.WeGoDent.forms.AppointmentForm;
import com.company.WeGoDent.entity.Appointment;
import com.company.WeGoDent.mapper.AppointmentMapper;
import com.company.WeGoDent.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {


    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Appointment> create(@RequestBody AppointmentForm appointmentForm){
        Appointment appointment = appointmentService.createAppointment(appointmentForm);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Appointment> update(@PathVariable Long id,@RequestBody AppointmentForm appointmentForm){
        Appointment appointment = appointmentService.updateAppointment(id,appointmentForm);
        if(appointment != null){
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{appointmentId}/treatment-phase/{treatmentPhaseId}")
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




}
