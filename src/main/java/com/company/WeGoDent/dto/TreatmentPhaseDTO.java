package com.company.WeGoDent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentPhaseDTO {

    String name;
    String description;
    Long id;
    TreatmentDTO treatmentDTO;
    LocalDateTime startDate;
    LocalDateTime endDate;


}
