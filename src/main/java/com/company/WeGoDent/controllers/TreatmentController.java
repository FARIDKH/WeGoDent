package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.TreatmentDTO;
import com.company.WeGoDent.dto.TreatmentPhaseDTO;
import com.company.WeGoDent.entity.Treatment;
import com.company.WeGoDent.entity.TreatmentPhase;
import com.company.WeGoDent.mapper.TreatmentMapper;
import com.company.WeGoDent.mapper.TreatmentPhaseMapper;
import com.company.WeGoDent.services.TreatmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/treatment")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private TreatmentMapper treatmentMapper;

    @Autowired
    private TreatmentPhaseMapper treatmentPhaseMapper;


    @PostMapping
    public ResponseEntity<TreatmentDTO> createTreatment(@Valid @RequestBody TreatmentDTO treatmentDTO){
        Treatment treatment = treatmentService.createTreatment(treatmentMapper.toEntity(treatmentDTO));
        return new ResponseEntity<>(treatmentMapper.toDto(treatment),HttpStatus.CREATED);
    }

    @GetMapping("/{treatmentId}")
    public ResponseEntity<TreatmentDTO> getTreatmentById(@PathVariable Long treatmentId){
        return ResponseEntity.ok(
                treatmentMapper.toDto(treatmentService.getById(treatmentId))
        );
    }

    @GetMapping("/{treatmentId}/phases")
    public ResponseEntity<List<TreatmentPhaseDTO>> getAllTreatmentPhasesOfTreatment(@PathVariable Long treatmentId){
        List<TreatmentPhase> treatmentPhaseList = treatmentService.getTreatmentPhasesOfTreatmentBy(treatmentId);
        List<TreatmentPhaseDTO> treatmentPhaseDTOts = treatmentPhaseMapper.toDto(treatmentPhaseList);

        return ResponseEntity.ok(treatmentPhaseDTOts);
    }

    @PutMapping
    public ResponseEntity<TreatmentDTO> updateTreatment(@PathVariable Long id, @Valid @RequestBody TreatmentDTO treatmentDTO){
        Treatment treatment = treatmentService.updateTreatment(treatmentMapper.toEntity(treatmentDTO));
        return ResponseEntity.ok(treatmentMapper.toDto(treatment));
    }

    @DeleteMapping("/{treatmentId}")
    public ResponseEntity<Boolean> deleteTreatment(@PathVariable Long treatmentId){
        Boolean l = treatmentService.deleteTreatment(treatmentId);
        if(l){
            return ResponseEntity.ok(Boolean.TRUE);
        }
        return (ResponseEntity<Boolean>) ResponseEntity.badRequest();
    }


}
