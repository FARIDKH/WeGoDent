package com.company.WeGoDent.dto;

import com.company.WeGoDent.entity.Patient;
import com.company.WeGoDent.entity.Plan;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PatientPlanDTO {



    private int id;



    private PlanDTO planDTO;


    private PatientDTO patientDTO;


    private LocalDateTime planStartDate;
    private LocalDateTime planEndDate;



}
