package com.company.WeGoDent.services;


import com.company.WeGoDent.entity.Appointment;
import com.company.WeGoDent.entity.TreatmentPhase;
import com.company.WeGoDent.entity.TreatmentSession;
import com.company.WeGoDent.repositories.TreatmentSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentSessionService {

    @Autowired
    private TreatmentSessionRepository treatmentSessionRepository;


    public TreatmentSession createTreatmentSession(Appointment appointment, TreatmentPhase treatmentPhase){

        TreatmentSession treatmentSession = new TreatmentSession();
        treatmentSession.setAppointment(appointment);
        treatmentSession.setTreatmentPhase(treatmentPhase);

        return treatmentSessionRepository.save(treatmentSession);

    }

    public TreatmentSession updateTreatmentSession(TreatmentSession treatmentSession){
        TreatmentSession treatmentSession1 = treatmentSessionRepository.getReferenceById(treatmentSession.getId());

        treatmentSession1.setTreatmentPhase(treatmentSession.getTreatmentPhase());
        treatmentSession1.setAppointment(treatmentSession.getAppointment());
        treatmentSession1.setNote(treatmentSession.getNote());

        return treatmentSessionRepository.save(treatmentSession1);
    }

    public boolean deleteTreatmentSession(Long id, TreatmentSession treatmentSession){
        if(!treatmentSessionRepository.existsById(id)) return false;
        treatmentSessionRepository.deleteById(id);
        return true;
    }

}
