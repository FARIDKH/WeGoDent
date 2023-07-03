package com.company.WeGoDent.controllers;

import com.company.WeGoDent.dto.InventoryCategoryDTO;
import com.company.WeGoDent.entity.InventoryCategory;
import com.company.WeGoDent.mapper.InventoryCategoryMapper;
import com.company.WeGoDent.services.InventoryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-categories")
public class InventoryCategoryController {

    @Autowired
    private InventoryCategoryService inventoryCategoryService;

    @Autowired
    private InventoryCategoryMapper inventoryCategoryMapper;

    @GetMapping
    public List<InventoryCategoryDTO> getAllInventoryCategories() {
        List<InventoryCategory> inventoryCategories = inventoryCategoryService.getAllInventoryCategories();
        return inventoryCategoryMapper.toDto(inventoryCategories);
    }

    @GetMapping("/{id}")
    public InventoryCategoryDTO getInventoryCategory(@PathVariable(value = "id") Long id) {
        InventoryCategory inventoryCategory = inventoryCategoryService.getInventoryCategory(id);
        return inventoryCategoryMapper.toDto(inventoryCategory);
    }

    @PostMapping
    public InventoryCategoryDTO createInventoryCategory(@RequestBody InventoryCategoryDTO inventoryCategoryDTO) {
        InventoryCategory inventoryCategory = inventoryCategoryMapper.toEntity(inventoryCategoryDTO);
        InventoryCategory createdInventoryCategory = inventoryCategoryService.createInventoryCategory(inventoryCategory);
        return inventoryCategoryMapper.toDto(createdInventoryCategory);
    }

    @PutMapping("/{id}")
    public InventoryCategoryDTO updateInventoryCategory(@PathVariable(value = "id") Long id, @RequestBody InventoryCategoryDTO inventoryCategoryDTO) {
        InventoryCategory inventoryCategory = inventoryCategoryMapper.toEntity(inventoryCategoryDTO);
        InventoryCategory updatedInventoryCategory = inventoryCategoryService.updateInventoryCategory(id, inventoryCategory);
        return inventoryCategoryMapper.toDto(updatedInventoryCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryCategory(@PathVariable(value = "id") Long id) {
        inventoryCategoryService.deleteInventoryCategory(id);
        return ResponseEntity.noContent().build();
    }
}
