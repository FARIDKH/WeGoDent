package com.company.WeGoDent.dto;

import lombok.Data;

import java.util.List;

@Data
public class InventoryCategoryDTO {
    private Long id;
    private String name;
    private List<InventoryItemDTO> inventoryItemDTOS;


}
