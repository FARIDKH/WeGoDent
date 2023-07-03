package com.company.WeGoDent.dto;

import lombok.Data;
import java.time.LocalDateTime;



@Data
public class SickLeaveReportDTO {

    private Long id;
    private PatientDTO patientDTO;
    private DoctorDTO doctorDTO;
    private Long reportContentId;
    private LocalDateTime reportDate;

}
