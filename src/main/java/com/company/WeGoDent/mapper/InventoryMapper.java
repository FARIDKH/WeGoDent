package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.InventoryDTO;
import com.company.WeGoDent.dto.InventoryItemDTO;
import com.company.WeGoDent.entity.Inventory;
import com.company.WeGoDent.entity.InventoryItem;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring", uses = {DoctorMapper.class, InventoryItemMapper.class})
public interface InventoryMapper extends EntityMapper<InventoryDTO, Inventory> {

    @Override
    @Mappings({
            @Mapping(source = "doctorDTO", target = "doctor"),
            @Mapping(source = "inventoryItemDTOS",target = "inventoryItems")
    })
    Inventory toEntity(InventoryDTO dto);

    @Override
    @Mappings({
            @Mapping(source = "doctor", target = "doctorDTO"),
            @Mapping(source = "inventoryItems",target = "inventoryItemDTOS")
    })
    InventoryDTO toDto(Inventory entity);
}
