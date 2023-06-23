package com.company.WeGoDent.mapper;


import com.company.WeGoDent.dto.PatientPlanDTO;
import com.company.WeGoDent.entity.PatientPlan;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, PlanMapper.class})
public interface PatientPlanMapper  extends EntityMapper<PatientPlanDTO, PatientPlan> {

    @Override
    @Mappings({
            @Mapping(source = "planDTO", target = "plan"),
            @Mapping(source = "patientDTO", target = "patient")
    })
    PatientPlan toEntity(PatientPlanDTO dto);

    @Override
    @Mappings({
            @Mapping(source = "plan", target = "planDTO"),
            @Mapping(source = "patient", target = "patientDTO")
    })
    PatientPlanDTO toDto(PatientPlan entity);
}
