package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.TreatmentPhaseDTO;
import com.company.WeGoDent.entity.TreatmentPhase;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TreatmentMapper.class)
public interface TreatmentPhaseMapper extends EntityMapper<TreatmentPhaseDTO, TreatmentPhase> {

//    TreatmentPhase toEntity(TreatmentPhaseDTO treatmentPhaseDTO);
//    TreatmentPhaseDTO toDTO(TreatmentPhase treatmentPhase);



}
