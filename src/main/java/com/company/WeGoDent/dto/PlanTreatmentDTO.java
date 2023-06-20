package com.company.WeGoDent.dto;

import com.company.WeGoDent.entity.Plan;
import com.company.WeGoDent.entity.Treatment;

public class PlanTreatmentDTO {
    public Long id;
    private Plan plan;
    private Treatment treatment;

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

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
}
