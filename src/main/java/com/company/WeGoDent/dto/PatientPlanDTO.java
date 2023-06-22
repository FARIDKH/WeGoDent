package com.company.WeGoDent.dto;

import com.company.WeGoDent.entity.Patient;
import com.company.WeGoDent.entity.Plan;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class PatientPlanDTO {



    public int id;



    public Plan plan;


    public Patient patient;


    public LocalDateTime planStartDate;
    public LocalDateTime planEndDate;



}
