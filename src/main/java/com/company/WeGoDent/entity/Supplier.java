package com.company.WeGoDent.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity(name = "suppliers")
@Data
@Table
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String contactInfo;

    // getters and setters...
}
