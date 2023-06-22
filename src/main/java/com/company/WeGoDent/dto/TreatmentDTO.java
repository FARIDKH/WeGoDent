package com.company.WeGoDent.dto;


import com.company.WeGoDent.entity.TreatmentPhase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TreatmentDTO  {

    Long id;
    String name;
    String description;
    Long cost;

    List<TreatmentPhaseDTO> treatmentPhaseList;


}
