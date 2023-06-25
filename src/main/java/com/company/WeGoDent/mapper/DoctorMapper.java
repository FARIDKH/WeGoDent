package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.DoctorDTO;
import com.company.WeGoDent.entity.Doctor;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import com.company.WeGoDent.services.helpers.GeocodingService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface DoctorMapper extends EntityMapper<DoctorDTO, Doctor> {

    @Override
    @Mapping(source = "userDTO", target = "user")
    Doctor toEntity(DoctorDTO dto);

    @Override
    @Mapping(source = "user", target = "userDTO")
    DoctorDTO toDto(Doctor entity);
}