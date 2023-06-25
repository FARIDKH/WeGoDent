package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.PatientDTO;
import com.company.WeGoDent.entity.Patient;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PatientMapper extends EntityMapper<PatientDTO, Patient> {

    @Override
    @Mapping(source = "userDTO", target = "user")
    Patient toEntity(PatientDTO dto);

    @Override
    @Mapping(source = "user", target = "userDTO")
    PatientDTO toDto(Patient entity);
}