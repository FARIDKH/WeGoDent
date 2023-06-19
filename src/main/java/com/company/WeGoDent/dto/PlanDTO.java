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

}
