package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.PrescriptionDTO;
import com.company.WeGoDent.entity.Prescription;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper extends EntityMapper<PrescriptionDTO, Prescription> {
}