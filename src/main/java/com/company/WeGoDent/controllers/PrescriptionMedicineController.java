package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.PrescriptionMedicineDTO;
import com.company.WeGoDent.mapper.PrescriptionMedicineMapper;
import com.company.WeGoDent.services.PrescriptionMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/prescription_medicines")
public class PrescriptionMedicineController {


    @Autowired
    private PrescriptionMedicineService prescriptionMedicineService;

    @Autowired
    private PrescriptionMedicineMapper prescriptionMedicineMapper;



    @GetMapping
    public ResponseEntity<List<PrescriptionMedicineDTO>> getAll() {
        return ResponseEntity.ok(prescriptionMedicineService.getAll().stream()
                .map(prescriptionMedicineMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionMedicineDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionMedicineMapper.toDto(prescriptionMedicineService.getOne(id)));
    }

    @PostMapping
    public ResponseEntity<PrescriptionMedicineDTO> save(@RequestBody PrescriptionMedicineDTO prescriptionMedicineDTO) {
        return ResponseEntity.ok(prescriptionMedicineMapper.toDto(prescriptionMedicineService.save(prescriptionMedicineMapper.toEntity(prescriptionMedicineDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionMedicineDTO> update(@PathVariable Long id, @RequestBody PrescriptionMedicineDTO prescriptionMedicineDTO) {
        return ResponseEntity.ok(prescriptionMedicineMapper.toDto(prescriptionMedicineService.update(id, prescriptionMedicineMapper.toEntity(prescriptionMedicineDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prescriptionMedicineService.delete(id);
        return ResponseEntity.ok().build();
    }
}

