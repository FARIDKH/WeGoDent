package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.Treatment;
import com.company.WeGoDent.entity.TreatmentPhase;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.repositories.TreatmentPhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentPhaseService {

    @Autowired
    private TreatmentPhaseRepository treatmentPhaseRepository;


    @Autowired
    private TreatmentService treatmentService;


    public TreatmentPhase createTreatmentPhase( TreatmentPhase treatmentPhase){
//        Treatment treatment = treatmentService.getById(treatmentId);
//        treatmentPhase.setTreatment(treatment);
        return treatmentPhaseRepository.save(treatmentPhase);
    }

    public TreatmentPhase updateTreatmentPhase(TreatmentPhase treatmentPhase){

        TreatmentPhase foundTP = treatmentPhaseRepository.getReferenceById(treatmentPhase.getId());
        foundTP.setTreatment(treatmentPhase.getTreatment());
        foundTP.setDescription(treatmentPhase.getDescription());
        foundTP.setName(treatmentPhase.getName());
        foundTP.setStartDate(treatmentPhase.getStartDate());
        foundTP.setEndDate(treatmentPhase.getEndDate());

        return treatmentPhaseRepository.save(foundTP);

    }

    public boolean deleteTreatmentPhase(Long id){
        if(!treatmentPhaseRepository.existsById(id)) return false;
        treatmentPhaseRepository.deleteById(id);
        return true;
    }

    public List<TreatmentPhase> findAll() {
        return treatmentPhaseRepository.findAll();
    }

    public TreatmentPhase findById(Long id) {
        return treatmentPhaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment phase not found with id " + id));

    }
}
