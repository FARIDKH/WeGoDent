package com.company.WeGoDent.controllers;

import com.company.WeGoDent.dto.InventoryItemDTO;
import com.company.WeGoDent.mapper.InventoryItemMapper;
import com.company.WeGoDent.mapper.InventoryMapper;
import com.company.WeGoDent.services.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/inventory-items")
public class InventoryItemController {

    @Autowired
    private InventoryItemService inventoryItemService;

    @Autowired
    private InventoryItemMapper inventoryItemMapper;

    @GetMapping
    public List<InventoryItemDTO> getAllInventoryItems() {
        return inventoryItemMapper.toDto(inventoryItemService.getAllInventoryItems());
    }

    @GetMapping("/{id}")
    public InventoryItemDTO getInventoryItem(@PathVariable(value = "id") Long id) {
        return inventoryItemMapper.toDto(inventoryItemService.getInventoryItem(id));
    }

    @PostMapping
    public InventoryItemDTO createInventoryItem(@RequestBody InventoryItemDTO inventoryItemDTO) {
        return inventoryItemMapper.toDto(inventoryItemService.createInventoryItem(inventoryItemMapper.toEntity(inventoryItemDTO)));
    }

    @PutMapping("/{id}")
    public InventoryItemDTO updateInventoryItem(@PathVariable(value = "id") Long id, @RequestBody InventoryItemDTO inventoryItemDTO) {
        return inventoryItemMapper.toDto(inventoryItemService.updateInventoryItem(id, inventoryItemMapper.toEntity(inventoryItemDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventoryItem(@PathVariable(value = "id") Long id) {
        inventoryItemService.deleteInventoryItem(id);
        return ResponseEntity.ok().build();
    }
}

