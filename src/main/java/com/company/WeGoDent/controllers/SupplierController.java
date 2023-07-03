package com.company.WeGoDent.controllers;

import com.company.WeGoDent.dto.SupplierDTO;
import com.company.WeGoDent.mapper.SupplierMapper;
import com.company.WeGoDent.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierMapper supplierMapper;

    @GetMapping
    public List<SupplierDTO> getAllSuppliers() {
        return supplierMapper.toDto(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    public SupplierDTO getSupplier(@PathVariable(value = "id") Long id) {
        return supplierMapper.toDto(supplierService.getSupplier(id));
    }

    @PostMapping
    public SupplierDTO createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierMapper.toDto(supplierService.createSupplier(supplierMapper.toEntity(supplierDTO)));
    }

    @PutMapping("/{id}")
    public SupplierDTO updateSupplier(@PathVariable(value = "id") Long id, @RequestBody SupplierDTO supplierDTO) {
        return supplierMapper.toDto(supplierService.updateSupplier(id, supplierMapper.toEntity(supplierDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable(value = "id") Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Deleted");
    }
}
