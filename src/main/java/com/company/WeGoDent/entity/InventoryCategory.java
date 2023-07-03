package com.company.WeGoDent.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "InventoryCategory")
@Table(name = "inventory_categories")
public class InventoryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<InventoryItem> inventoryItems;
}

