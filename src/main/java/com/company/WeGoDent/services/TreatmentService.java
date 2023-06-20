package com.company.WeGoDent.services;


import com.company.WeGoDent.entity.Treatment;
import com.company.WeGoDent.entity.TreatmentPhase;
import com.company.WeGoDent.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    public Treatment getById(Long id){
        if(!treatmentRepository.existsById(id)) return null;
        return treatmentRepository.getReferenceById(id);
    }

    public List<TreatmentPhase> getTreatmentPhasesOfTreatmentBy(Long id){
        if(!treatmentRepository.existsById(id)) return null;
        Treatment treatment = treatmentRepository.getReferenceById(id);
        return treatment.getTreatmentPhaseList();
    }



    public Treatment createTreatment(Treatment treatment){
        return treatmentRepository.save(treatment);
    }

    public Treatment updateTreatment(Treatment treatment){
        Treatment foundTreatment = treatmentRepository.getReferenceById(treatment.getId());

        foundTreatment.setCost(treatment.getCost());
        foundTreatment.setTreatmentPhaseList(treatment.getTreatmentPhaseList());
        foundTreatment.setName(treatment.getName());
        foundTreatment.setDescription(treatment.getDescription());

        return treatmentRepository.save(foundTreatment);
    }

    public Boolean deleteTreatment(Long id){
        if(treatmentRepository.existsById(id)){
            treatmentRepository.deleteById(id);
            return true;
        }
        return false;
    }




}
