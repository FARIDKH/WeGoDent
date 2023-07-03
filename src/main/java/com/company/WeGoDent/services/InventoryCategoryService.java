package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.InventoryCategory;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.repositories.InventoryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryCategoryService {

    @Autowired
    private InventoryCategoryRepository inventoryCategoryRepository;

    public List<InventoryCategory> getAllInventoryCategories() {
        return inventoryCategoryRepository.findAll();
    }

    public InventoryCategory getInventoryCategory(Long id) {
        return inventoryCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("InventoryCategory not found with id "  + id));
    }

    public InventoryCategory createInventoryCategory(InventoryCategory inventoryCategory) {
        return inventoryCategoryRepository.save(inventoryCategory);
    }

    public InventoryCategory updateInventoryCategory(Long id, InventoryCategory inventoryCategoryDetails) {
        InventoryCategory inventoryCategory = inventoryCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("InventoryCategory not found with id "  + id));

        inventoryCategory.setName(inventoryCategoryDetails.getName());

        return inventoryCategoryRepository.save(inventoryCategory);
    }

    public void deleteInventoryCategory(Long id) {
        InventoryCategory inventoryCategory = inventoryCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("InventoryCategory not found with id "  + id));
        inventoryCategoryRepository.delete(inventoryCategory);
    }
}