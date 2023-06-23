package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.PatientDTO;
import com.company.WeGoDent.entity.Patient;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper extends EntityMapper<PatientDTO, Patient> {

}