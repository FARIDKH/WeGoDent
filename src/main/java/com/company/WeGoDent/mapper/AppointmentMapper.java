package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.AppointmentDTO;
import com.company.WeGoDent.entity.Appointment;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import com.company.WeGoDent.services.helpers.GeocodingService;
import org.geolatte.geom.Point;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",uses = {DoctorMapper.class, PatientMapper.class})
public interface AppointmentMapper extends EntityMapper<AppointmentDTO, Appointment> {

    @Mapping(source = "doctorDTO", target = "doctor")
    @Mapping(source = "patientDTO", target = "patient")
    Appointment toEntity(AppointmentDTO appointmentDTO);

    @Mapping(source = "doctor", target = "doctorDTO")
    @Mapping(source = "patient", target = "patientDTO")
    AppointmentDTO toDto(Appointment appointment);

    default Appointment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setId(id);
        return appointment;
    }


}
