package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.PrescriptionMedicineDTO;
import com.company.WeGoDent.entity.PrescriptionMedicine;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PrescriptionMapper.class, MedicineMapper.class})
public interface PrescriptionMedicineMapper extends EntityMapper<PrescriptionMedicineDTO, PrescriptionMedicine> {

    @Override
    @Mapping(source = "prescriptionDTO", target = "prescription")
    @Mapping(source = "medicineDTO", target = "medicine")
    PrescriptionMedicine toEntity(PrescriptionMedicineDTO dto);

    @Override
    @Mapping(source = "medicine", target = "medicineDTO")
    @Mapping(source = "prescription", target = "prescriptionDTO")
    PrescriptionMedicineDTO toDto(PrescriptionMedicine entity);
}
