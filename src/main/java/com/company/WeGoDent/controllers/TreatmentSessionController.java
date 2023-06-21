package com.company.WeGoDent.controllers;

import com.company.WeGoDent.dto.TreatmentSessionDTO;
import com.company.WeGoDent.entity.TreatmentSession;
import com.company.WeGoDent.mapper.TreatmentSessionMapper;
import com.company.WeGoDent.services.TreatmentSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/treatment-session")
public class TreatmentSessionController {

    @Autowired
    private TreatmentSessionService treatmentSessionService;


    @Autowired
    private TreatmentSessionMapper treatmentSessionMapper;


    @GetMapping
    public ResponseEntity<List<TreatmentSession>> getTreatmentSessions(@RequestParam(required = false) Long patientId,
                                                                       @RequestParam(required = false) String treatmentName) {
        List<TreatmentSession> treatmentSessions;

        if (patientId != null) {
            treatmentSessions = treatmentSessionService.findByPatientId(patientId);
        } else if (treatmentName != null) {
            treatmentSessions = treatmentSessionService.findByTreatmentName(treatmentName);
        } else {
            treatmentSessions = treatmentSessionService.findAll();
        }

        if (treatmentSessions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(treatmentSessions, HttpStatus.OK);
    }

}
