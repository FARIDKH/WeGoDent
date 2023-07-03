package com.company.WeGoDent.entity;

import com.company.WeGoDent.enums.InventoryItemStatus;
import lombok.Data;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Data
@Entity(name = "InventoryItem")
@Table(name = "inventory_items")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @Enumerated(EnumType.ORDINAL)
    private InventoryItemStatus status;

    @ManyToOne
    private Inventory inventory;

    @ManyToOne
    private InventoryCategory category;

    @ManyToOne
    private Supplier supplier;
}
