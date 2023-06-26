package com.company.WeGoDent.dto;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrescriptionDTO {
    private Long id;
    private PatientDTO patientDTO;
    private DoctorDTO doctorDTO;
    private String protocolNumber;
    private LocalDateTime prescriptionDate;
}
