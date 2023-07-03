package com.company.WeGoDent.dto;

import lombok.Data;

@Data
public class InventoryItemDTO {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
}

