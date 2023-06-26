package com.company.WeGoDent.controllers;

import com.company.WeGoDent.dto.MedicineDTO;
import com.company.WeGoDent.entity.Medicine;
import com.company.WeGoDent.mapper.MedicineMapper;
import com.company.WeGoDent.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/medicines")
public class MedicineController {


    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineMapper medicineMapper;



    @GetMapping
    public ResponseEntity<List<MedicineDTO>> getAll() {
        return ResponseEntity.ok(medicineMapper.toDto(medicineService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(medicineMapper.toDto(medicineService.getOne(id)));
    }

    @PostMapping
    public ResponseEntity<MedicineDTO> save(@RequestBody MedicineDTO medicineDTO) {
        return ResponseEntity.ok(medicineMapper.toDto(medicineService.save(medicineMapper.toEntity(medicineDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineDTO> update(@PathVariable Long id, @RequestBody MedicineDTO medicineDTO) {
        return ResponseEntity.ok(medicineMapper.toDto(medicineService.update(id, medicineMapper.toEntity(medicineDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicineService.delete(id);
        return ResponseEntity.ok().build();
    }
}



