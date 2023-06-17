package com.company.WeGoDent.services;


import com.company.WeGoDent.entity.TreatmentSession;
import com.company.WeGoDent.repositories.TreatmentSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentSessionService {

    @Autowired
    private TreatmentSessionRepository treatmentSessionRepository;

    public TreatmentSession createTreatmentSession(TreatmentSession treatmentSession){
        return treatmentSessionRepository.save(treatmentSession);
    }

    public TreatmentSession updateTreatmentSession(Long id, TreatmentSession treatmentSession){
        if(!treatmentSessionRepository.existsById(id)) return null;

        treatmentSession.setTreatmentPhase(treatmentSession.getTreatmentPhase());
        treatmentSession.setAppointment(treatmentSession.getAppointment());
        treatmentSession.setNote(treatmentSession.getNote());

        return treatmentSessionRepository.save(treatmentSession);
    }

    public boolean deleteTreatmentSession(Long id, TreatmentSession treatmentSession){
        if(!treatmentSessionRepository.existsById(id)) return false;
        treatmentSessionRepository.deleteById(id);
        return true;
    }

}
