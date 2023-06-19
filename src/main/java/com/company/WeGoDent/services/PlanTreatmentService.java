package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.Plan;
import com.company.WeGoDent.entity.PlanTreatment;
import com.company.WeGoDent.entity.Treatment;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.repositories.PlanTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanTreatmentService {


    @Autowired
    private PlanTreatmentRepository planTreatmentRepository;

    public PlanTreatment create(PlanTreatment planTreatment){
        return planTreatmentRepository.saveAndFlush(planTreatment);
    }

    public PlanTreatment update(PlanTreatment planTreatment){
        PlanTreatment foundPT = planTreatmentRepository.findById(planTreatment.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Plan treatment not found with id : " + planTreatment.getId())
        );

        foundPT.setPlan(planTreatment.getPlan());
        foundPT.setTreatment(planTreatment.getTreatment());
        return planTreatmentRepository.save(foundPT);
    }

    public Boolean delete(Long id){
        PlanTreatment foundPT = planTreatmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Plan treatment not found with id : " + id)
        );

        planTreatmentRepository.deleteById(id);

        return true;
    }

    public void addTreatmentToPlan(Plan plan, Treatment treatment) {
        PlanTreatment planTreatment = new PlanTreatment();
        planTreatment.setPlan(plan);
        planTreatment.setTreatment(treatment);
        planTreatmentRepository.save(planTreatment);
    }

}
