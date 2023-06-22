package com.company.WeGoDent.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class TreatmentSessionDTO {

    TreatmentPhaseDTO treatmentPhaseDTO;
    AppointmentDTO appointmentDTO;
    Long id;
    String note;


}
