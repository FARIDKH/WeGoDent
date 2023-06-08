package com.company.WeGoDent.forms;

import com.company.WeGoDent.models.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentForm {

    public LocalDateTime appointmentStart;
    public LocalDateTime appointmentEnd;
    public AppointmentStatus appointmentStatus = AppointmentStatus.REQUESTED;
    public Long doctor_id;
    public Long patient_id;

}
