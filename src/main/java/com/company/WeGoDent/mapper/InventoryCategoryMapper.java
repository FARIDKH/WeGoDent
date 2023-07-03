package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.InventoryCategoryDTO;
import com.company.WeGoDent.entity.InventoryCategory;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {InventoryMapper.class})
public interface InventoryCategoryMapper extends EntityMapper<InventoryCategoryDTO, InventoryCategory> {

    @Override
    @Mapping(source = "inventoryItemDTOS", target = "inventoryItems")
    InventoryCategory toEntity(InventoryCategoryDTO dto);

    @Override
    @Mapping(source = "inventoryItems", target = "inventoryItemDTOS")
    InventoryCategoryDTO toDto(InventoryCategory entity);
}
