package com.company.WeGoDent.dto;

import lombok.Data;

@Data
public class MedicineDTO {
    private Long id;
    private String name;
    private String dosage;
    private String instructions;
}
