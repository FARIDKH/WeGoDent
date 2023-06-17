package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.TreatmentPhaseDTO;
import com.company.WeGoDent.entity.TreatmentPhase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TreatmentMapper.class)
public interface TreatmentPhaseMapper {

    TreatmentPhase toEntity(TreatmentPhaseDTO treatmentPhaseDTO);
    TreatmentPhaseDTO toDTO(TreatmentPhase treatmentPhase);

}
