package com.company.WeGoDent.dto;



import lombok.Data;

import java.util.List;

@Data
public class InventoryDTO {

    private Long id;
    private DoctorDTO doctorDTO;
    private List<InventoryItemDTO> inventoryItemDTOS;

}
