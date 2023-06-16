package com.company.WeGoDent.forms;

import com.company.WeGoDent.enums.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentForm {

    public LocalDateTime appointmentStart;
    public LocalDateTime appointmentEnd;
    public AppointmentStatus appointmentStatus;
    public Long doctor_id;
    public Long patient_id;

}
