package com.company.WeGoDent.dto;

import com.company.WeGoDent.entity.Plan;
import com.company.WeGoDent.entity.Treatment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PlanTreatmentDTO {
    public Long id;
    private PlanDTO planDTO;
    private TreatmentDTO treatmentDTO;
}
