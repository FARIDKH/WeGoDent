package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.AppointmentDTO;
import com.company.WeGoDent.entity.Appointment;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {DoctorMapper.class, PatientMapper.class})
public interface AppointmentMapper extends EntityMapper<AppointmentDTO, Appointment> {

    @Mapping(source = "doctor_id", target = "doctor")
    @Mapping(source = "patient_id", target = "patient")
    Appointment toEntity(AppointmentDTO appointmentDTO);

    @Mapping(source = "doctor.id", target = "doctor_id")
    @Mapping(source = "patient.id", target = "patient_id")
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
