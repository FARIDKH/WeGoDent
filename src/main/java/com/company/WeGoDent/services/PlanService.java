package com.company.WeGoDent.services;


import com.company.WeGoDent.entity.Plan;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {


    @Autowired
    private PlanRepository planRepository;

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


    public Plan getPlanById(Long id){
        Plan selectedPlan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id" + id));

        return selectedPlan;
    }

    public Boolean delete(Long id){
        Plan selectedPlan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id" + id));
        planRepository.deleteById(id);
        return true;
    }






}


