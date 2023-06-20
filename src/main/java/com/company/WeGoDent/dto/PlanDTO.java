package com.company.WeGoDent.dto;

import com.company.WeGoDent.entity.PlanTreatment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class PlanDTO {

    public Long id;

    public String name;
    public String description;


    public List<PlanTreatment> planTreatments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PlanTreatment> getPlanTreatments() {
        return planTreatments;
    }

    public void setPlanTreatments(List<PlanTreatment> planTreatments) {
        this.planTreatments = planTreatments;
    }
}
