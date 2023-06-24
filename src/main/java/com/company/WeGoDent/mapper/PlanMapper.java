package com.company.WeGoDent.mapper;


import com.company.WeGoDent.dto.PlanDTO;
import com.company.WeGoDent.entity.Plan;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlanMapper extends EntityMapper<PlanDTO, Plan> {
}
