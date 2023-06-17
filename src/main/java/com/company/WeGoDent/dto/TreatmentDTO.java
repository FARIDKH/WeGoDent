package com.company.WeGoDent.dto;


import com.company.WeGoDent.entity.TreatmentPhase;

import java.util.List;

public class TreatmentDTO {

    Long id;
    String name;
    String description;
    Long cost;

    List<TreatmentPhase> treatmentPhaseList;

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

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}