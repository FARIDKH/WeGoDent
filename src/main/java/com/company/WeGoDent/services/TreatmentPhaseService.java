package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.Treatment;
import com.company.WeGoDent.entity.TreatmentPhase;
import com.company.WeGoDent.repositories.TreatmentPhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentPhaseService {

    @Autowired
    private TreatmentPhaseRepository treatmentPhaseRepository;

    public TreatmentPhase createTreatmentPhase(TreatmentPhase treatmentPhase){
        return treatmentPhaseRepository.save(treatmentPhase);
    }

    public TreatmentPhase updateTreatmentPhase(Long id, TreatmentPhase treatmentPhase){
        if(!treatmentPhaseRepository.existsById(id)) return null;

        TreatmentPhase foundTP = treatmentPhaseRepository.getReferenceById(id);
        foundTP.setTreatment(treatmentPhase.getTreatment());
        foundTP.setDescription(treatmentPhase.getDescription());
        foundTP.setName(treatmentPhase.getName());
        foundTP.setStartDate(treatmentPhase.getStartDate());
        foundTP.setEndDate(treatmentPhase.getEndDate());

        return treatmentPhaseRepository.save(foundTP);

    }

    public boolean deleteTreatmentPhase(Long id, TreatmentPhase treatmentPhase){
        if(!treatmentPhaseRepository.existsById(id)) return false;
        treatmentPhaseRepository.deleteById(id);
        return true;
    }

}
