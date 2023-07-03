package com.company.WeGoDent.controllers;

import com.company.WeGoDent.dto.InventoryDTO;
import com.company.WeGoDent.entity.Inventory;
import com.company.WeGoDent.mapper.InventoryItemMapper;
import com.company.WeGoDent.mapper.InventoryMapper;
import com.company.WeGoDent.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private  InventoryMapper inventoryMapper;
    @Autowired
    private  InventoryItemMapper inventoryItemMapper;




    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        List<InventoryDTO> inventoryDtos = inventories.stream()
                .map(inventoryMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(inventoryDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable("id") Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        if (inventory != null) {
            InventoryDTO inventoryDto = inventoryMapper.toDto(inventory);
            return ResponseEntity.ok(inventoryDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO inventoryDto) {
        Inventory inventory = inventoryMapper.toEntity(inventoryDto);
        Inventory createdInventory = inventoryService.createInventory(inventory);
        InventoryDTO createdInventoryDto = inventoryMapper.toDto(createdInventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInventoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable("id") Long id,
                                                        @RequestBody InventoryDTO inventoryDto) {
        Inventory inventory = inventoryMapper.toEntity(inventoryDto);
        Inventory updatedInventory = inventoryService.updateInventory(id, inventory);
        if (updatedInventory != null) {
            InventoryDTO updatedInventoryDto = inventoryMapper.toDto(updatedInventory);
            return ResponseEntity.ok(updatedInventoryDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable("id") Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}