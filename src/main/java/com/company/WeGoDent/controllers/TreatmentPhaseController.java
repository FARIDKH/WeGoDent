package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.TreatmentPhaseDTO;
import com.company.WeGoDent.entity.TreatmentPhase;
import com.company.WeGoDent.mapper.TreatmentPhaseMapper;
import com.company.WeGoDent.services.TreatmentPhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/treatment-phases")
public class TreatmentPhaseController {

    @Autowired

    private  TreatmentPhaseService treatmentPhaseService;

    @Autowired
    private  TreatmentPhaseMapper treatmentPhaseMapper;



    @PostMapping
    public ResponseEntity<TreatmentPhaseDTO> createTreatmentPhase(@RequestBody TreatmentPhaseDTO treatmentPhaseDTO) throws URISyntaxException {
        TreatmentPhase savedTreatmentPhase = treatmentPhaseService.createTreatmentPhase(treatmentPhaseMapper.toEntity(treatmentPhaseDTO));
        return ResponseEntity.created(new URI("/api/treatment-phases/" + savedTreatmentPhase.getId()))
                .body(treatmentPhaseMapper.toDto(savedTreatmentPhase));
    }

    @GetMapping
    public List<TreatmentPhaseDTO> getAllTreatmentPhases() {
        return treatmentPhaseMapper.toDto(treatmentPhaseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentPhaseDTO> getTreatmentPhase(@PathVariable Long id) {
        TreatmentPhase treatmentPhase = treatmentPhaseService.findById(id);
        return ResponseEntity.ok(treatmentPhaseMapper.toDto(treatmentPhase));
    }

    @PutMapping("/")
    public ResponseEntity<TreatmentPhaseDTO> updateTreatmentPhase(@RequestBody TreatmentPhaseDTO treatmentPhaseDTO) {
        TreatmentPhase updatedTreatmentPhase = treatmentPhaseService.updateTreatmentPhase(treatmentPhaseMapper.toEntity(treatmentPhaseDTO));
        return ResponseEntity.ok().body(treatmentPhaseMapper.toDto(updatedTreatmentPhase));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatmentPhase(@PathVariable Long id) {
        treatmentPhaseService.deleteTreatmentPhase(id);
        return ResponseEntity.ok().build();
    }
}
