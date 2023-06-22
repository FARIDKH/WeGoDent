package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.TreatmentPhaseDTO;
import com.company.WeGoDent.entity.TreatmentPhase;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TreatmentMapper.class)
public interface TreatmentPhaseMapper extends EntityMapper<TreatmentPhaseDTO, TreatmentPhase> {


    @Override
    @Mapping(source = "treatmentDTO", target = "treatment")
    TreatmentPhase toEntity(TreatmentPhaseDTO dto);


    @Override
    @Mapping(source = "treatment", target = "treatmentDTO")
    TreatmentPhaseDTO toDto(TreatmentPhase entity);
}
