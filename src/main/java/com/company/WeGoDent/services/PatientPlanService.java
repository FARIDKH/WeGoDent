package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.Patient;
import com.company.WeGoDent.entity.PatientPlan;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.repositories.PatientPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientPlanService {

    @Autowired
    private PatientPlanRepository patientPlanRepository;

    public PatientPlan create(PatientPlan patientPlan){
        return patientPlanRepository.saveAndFlush(patientPlan);
    }

    public PatientPlan update(PatientPlan patientPlan){
        PatientPlan foundPP = patientPlanRepository.findById(patientPlan.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Patient plan not found with PatientPlan id of " + patientPlan.getId())
        );

        foundPP.setPatient(patientPlan.getPatient());
        foundPP.setPlan(patientPlan.getPlan());
        foundPP.setPlanEndDate(patientPlan.getPlanStartDate());
        foundPP.setPlanEndDate(patientPlan.getPlanEndDate());

        return patientPlanRepository.save(foundPP);

    }

    public Boolean delete(Long id){
        PatientPlan foundPP = patientPlanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Patient plan not found with PatientPlan id of " + id)
        );

        return true;
    }
}
