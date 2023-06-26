package com.company.WeGoDent.dto;

import lombok.Data;

@Data
public class PrescriptionMedicineDTO {
    private Long id;
    private PrescriptionDTO prescriptionDTO;
    private MedicineDTO medicineDTO;
    private Integer quantity;
}