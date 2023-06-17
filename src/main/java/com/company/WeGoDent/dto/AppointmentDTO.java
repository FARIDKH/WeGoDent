package com.company.WeGoDent.dto;

import com.company.WeGoDent.enums.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentDTO {

    public LocalDateTime appointmentStart;
    public LocalDateTime appointmentEnd;
    public AppointmentStatus appointmentStatus;
    public Long doctor_id;
    public Long patient_id;
    public Long id;

}
