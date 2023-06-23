package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.DoctorDTO;
import com.company.WeGoDent.entity.Doctor;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import com.company.WeGoDent.services.helpers.GeocodingService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

@Mapper(componentModel = "spring")
public interface DoctorMapper extends EntityMapper<DoctorDTO, Doctor> {
    // Your other mapping methods...

//    default Doctor fromId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        Doctor doctor = new Doctor();
//        doctor.setId(id);
//        return doctor;
//    }




}