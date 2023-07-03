package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.InventoryItem;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.repositories.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryItemService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    public InventoryItem getInventoryItem(Long id) {
        return inventoryItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("InventoryItem not found with" + id));
    }

    public InventoryItem createInventoryItem(InventoryItem inventoryItem) {
        return inventoryItemRepository.save(inventoryItem);
    }

    public InventoryItem updateInventoryItem(Long id, InventoryItem inventoryDetails) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("InventoryItem not found with" + id));

        inventoryItem.setName(inventoryDetails.getName());
        inventoryItem.setDescription(inventoryDetails.getDescription());
        inventoryItem.setPrice(inventoryDetails.getPrice());
        inventoryItem.setStatus(inventoryDetails.getStatus());
        inventoryItem.setCategory(inventoryDetails.getCategory());
        inventoryItem.setSupplier(inventoryDetails.getSupplier());

        return inventoryItemRepository.save(inventoryItem);
    }

    public void deleteInventoryItem(Long id) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("InventoryItem not found with" + id));
        inventoryItemRepository.delete(inventoryItem);
    }
}
