package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.MedicineDTO;
import com.company.WeGoDent.entity.Medicine;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicineMapper extends EntityMapper<MedicineDTO, Medicine> {
}
