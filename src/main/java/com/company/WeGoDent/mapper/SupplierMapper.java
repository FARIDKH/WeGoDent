package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.SupplierDTO;
import com.company.WeGoDent.entity.Supplier;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper extends EntityMapper<SupplierDTO, Supplier> {
}

