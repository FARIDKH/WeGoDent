package com.company.WeGoDent.services;


import com.company.WeGoDent.entity.Inventory;
import com.company.WeGoDent.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existingInventory = inventoryRepository.findById(id).orElse(null);
        if (existingInventory != null) {
            existingInventory.setDoctor(inventory.getDoctor());
            existingInventory.setInventoryItems(inventory.getInventoryItems());
            return inventoryRepository.save(existingInventory);
        }
        return null;
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}

