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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(LocalDateTime planStartDate) {
        this.planStartDate = planStartDate;
    }

    public LocalDateTime getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(LocalDateTime planEndDate) {
        this.planEndDate = planEndDate;
    }
}
