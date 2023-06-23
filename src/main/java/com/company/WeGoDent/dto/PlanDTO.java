package com.company.WeGoDent.dto;

import com.company.WeGoDent.entity.PlanTreatment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PlanDTO {

    private Long id;

    private String name;
    private String description;



}
