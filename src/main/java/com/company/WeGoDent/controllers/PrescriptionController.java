package com.company.WeGoDent.controllers;

import com.company.WeGoDent.dto.PrescriptionDTO;
import com.company.WeGoDent.entity.Prescription;
import com.company.WeGoDent.mapper.PrescriptionMapper;
import com.company.WeGoDent.services.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/prescriptions")
public class PrescriptionController {


    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PrescriptionMapper prescriptionMapper;



    @GetMapping
    public ResponseEntity<List<PrescriptionDTO>> getAll() {
        return ResponseEntity.ok(prescriptionService.getAll().stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionMapper.toDto(prescriptionService.getOne(id)));
    }

    @PostMapping
    public ResponseEntity<PrescriptionDTO> save(@RequestBody PrescriptionDTO prescriptionDTO) {
        return ResponseEntity.ok(prescriptionMapper.toDto(prescriptionService.save(prescriptionMapper.toEntity(prescriptionDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> update(@PathVariable Long id, @RequestBody PrescriptionDTO prescriptionDTO) {
        return ResponseEntity.ok(prescriptionMapper.toDto(prescriptionService.update(id, prescriptionMapper.toEntity(prescriptionDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prescriptionService.delete(id);
        return ResponseEntity.ok().build();
    }
}

