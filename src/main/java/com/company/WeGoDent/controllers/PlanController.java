package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.PlanDTO;
import com.company.WeGoDent.dto.PlanTreatmentDTO;
import com.company.WeGoDent.entity.Plan;
import com.company.WeGoDent.entity.PlanTreatment;
import com.company.WeGoDent.mapper.PlanMapper;
import com.company.WeGoDent.mapper.PlanTreatmentMapper;
import com.company.WeGoDent.services.PlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private PlanTreatmentMapper planTreatmentMapper;





    @GetMapping("/{planId}/treatments")
    public ResponseEntity<List<PlanTreatmentDTO>> getListOfPlanTreatments(
            @PathVariable Long planId
    ){
        return ResponseEntity.ok(
                planTreatmentMapper.toDto(
                        planService.getListOfTreatmentsBy(planId)
                )
        );
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanDTO> getByID(@PathVariable Long planId,@Valid @RequestBody PlanDTO planDTO){

        return ResponseEntity.ok(
                planMapper.toDto(planService.getPlanById(planId))
        );

    }

    @PostMapping
    public ResponseEntity<PlanDTO> create(@Valid @RequestBody PlanDTO planDTO){
        return ResponseEntity.ok(
                planMapper.toDto(planService.create(planMapper.toEntity(planDTO)))
        );
    }

    @PutMapping
    public ResponseEntity<PlanDTO> update(@Valid @RequestBody PlanDTO planDTO){

        return ResponseEntity.ok(
                planMapper.toDto(planService.update(planMapper.toEntity(planDTO)))
                );

    }



    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deleteByID(@PathVariable Long planId){

        return ResponseEntity.ok(
                planService.delete(planId)
        );

    }




}
