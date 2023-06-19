package com.company.WeGoDent.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_plans")
public class PatientPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Other fields...

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    private LocalDateTime planStartDate;
    private LocalDateTime planEndDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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