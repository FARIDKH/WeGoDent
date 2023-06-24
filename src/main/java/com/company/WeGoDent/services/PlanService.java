package com.company.WeGoDent.services;


import com.company.WeGoDent.entity.Plan;
import com.company.WeGoDent.entity.PlanTreatment;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {


    @Autowired
    private PlanRepository planRepository;



    public Plan getPlanById(Long id){
        Plan selectedPlan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id" + id));

        return selectedPlan;
    }


    public List<PlanTreatment> getListOfTreatmentsBy(Long id){
        Plan selectedPlan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id" + id));

        return selectedPlan.getPlanTreatments();

    }




    public Plan create(Plan plan){
        return planRepository.saveAndFlush(plan);
    }


    public Plan update(Plan plan){
        Plan selectedPlan = planRepository.findById(plan.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id" + plan.getId()));

        selectedPlan.setPlanTreatments(plan.getPlanTreatments());
        selectedPlan.setDescription(plan.getDescription());
        selectedPlan.setName(plan.getName());

        return planRepository.save(plan);

    }




    public Boolean delete(Long id){
        planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id" + id));
        planRepository.deleteById(id);
        return true;
    }



}


