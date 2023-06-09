package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.TreatmentSessionDTO;
import com.company.WeGoDent.entity.Appointment;
import com.company.WeGoDent.entity.TreatmentPhase;
import com.company.WeGoDent.entity.TreatmentSession;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {AppointmentMapper.class, TreatmentPhaseMapper.class})
public interface TreatmentSessionMapper extends EntityMapper<TreatmentSessionDTO, TreatmentSession> {

    @Mapping(source = "treatmentPhase", target = "treatmentPhaseDTO")
    @Mapping(source = "appointment", target = "appointmentDTO")
    TreatmentSessionDTO toDto(TreatmentSession treatmentSession);

    @Mapping(source = "treatmentPhaseDTO", target = "treatmentPhase")
    @Mapping(source = "appointmentDTO", target = "appointment")
    TreatmentSession toEntity(TreatmentSessionDTO treatmentSessionDTO);

//    @Named("idToTreatmentPhase")
//    default TreatmentPhase idToTreatmentPhase(Long id) {
//        if (id == null) {
//            return null;
//        }
//        TreatmentPhase treatmentPhase = new TreatmentPhase();
//        treatmentPhase.setId(id);
//        return treatmentPhase;
//    }
//
//    @Named("idToAppointment")
//    default Appointment idToAppointment(Long id) {
//        if (id == null) {
//            return null;
//        }
//        Appointment appointment = new Appointment();
//        appointment.setId(id);
//        return appointment;
//    }

}
