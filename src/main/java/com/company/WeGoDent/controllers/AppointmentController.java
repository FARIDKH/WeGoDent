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
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentForm appointmentForm){
        Appointment appointment = appointmentService.createAppointment(appointmentForm);
        return new ResponseEntity<>(
                appointmentMapper.toDto(appointment)
                , HttpStatus.OK);
    }

    @PutMapping("/{appointmentId}")
    @ResponseBody
    public ResponseEntity<AppointmentDTO> update(@PathVariable Long appointmentId,@RequestBody AppointmentForm appointmentForm){
        Appointment appointment = appointmentService.updateAppointment(appointmentId,appointmentForm);
        if(appointment != null){
            return new ResponseEntity<>( appointmentMapper.toDto(appointment), HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }






}
