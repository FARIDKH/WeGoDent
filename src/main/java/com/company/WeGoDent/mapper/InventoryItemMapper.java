package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.InventoryItemDTO;
import com.company.WeGoDent.entity.InventoryItem;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper extends EntityMapper<InventoryItemDTO, InventoryItem> {

    @Override
    @Mapping(source = "categoryId", target = "category.id")
    InventoryItem toEntity(InventoryItemDTO dto);

    @Override
    @Mapping(source = "category.id", target = "categoryId")
    InventoryItemDTO toDto(InventoryItem entity);
}
