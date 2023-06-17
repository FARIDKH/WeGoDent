package com.company.WeGoDent.services;


import com.company.WeGoDent.entity.Treatment;
import com.company.WeGoDent.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    public Treatment createTreatment(Treatment treatment){
        return treatmentRepository.save(treatment);
    }

    public Treatment updateTreatment(Long id,Treatment treatment){
        if(!treatmentRepository.existsById(id)) return  null; // guard statement
        Treatment foundTreatment = treatmentRepository.getReferenceById(id);

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
