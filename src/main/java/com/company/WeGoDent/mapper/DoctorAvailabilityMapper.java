package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.DoctorAvailabilityDTO;
import com.company.WeGoDent.entity.DoctorAvailability;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface DoctorAvailabilityMapper extends EntityMapper<DoctorAvailabilityDTO, DoctorAvailability> {
}
