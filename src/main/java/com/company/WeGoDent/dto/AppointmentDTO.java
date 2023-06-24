package com.company.WeGoDent.dto;

import com.company.WeGoDent.enums.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentDTO {

    public LocalDateTime appointmentStart;
    public LocalDateTime appointmentEnd;
    public AppointmentStatus status;
    public DoctorDTO doctorDTO;
    public PatientDTO patientDTO;
    public Long id;

}
