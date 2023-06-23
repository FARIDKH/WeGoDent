package com.company.WeGoDent.mapper;


import com.company.WeGoDent.dto.PlanTreatmentDTO;
import com.company.WeGoDent.entity.PlanTreatment;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {TreatmentMapper.class, PlanMapper.class})
public interface PlanTreatmentMapper extends EntityMapper<PlanTreatmentDTO, PlanTreatment> {

    @Override
    @Mappings({
            @Mapping(source = "treatmentDTO", target = "treatment"),
            @Mapping(source = "planDTO", target = "plan")
    })
    PlanTreatment toEntity(PlanTreatmentDTO dto);

    @Override
    @Mappings({
            @Mapping(source = "treatment", target = "treatmentDTO"),
            @Mapping(source = "plan", target = "planDTO")
    })
    PlanTreatmentDTO toDto(PlanTreatment entity);
}
