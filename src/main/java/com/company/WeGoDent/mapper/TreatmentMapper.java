package com.company.WeGoDent.mapper;


import com.company.WeGoDent.dto.TreatmentDTO;
import com.company.WeGoDent.dto.TreatmentPhaseDTO;
import com.company.WeGoDent.entity.Treatment;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { TreatmentPhaseMapper.class })
public interface TreatmentMapper extends EntityMapper<TreatmentDTO, Treatment> {

    Treatment toEntity(TreatmentPhaseDTO treatmentPhaseDTO);

//    @Mapping(source = "id", target = "id")
//    Treatment toEntity(TreatmentDTO treatmentDTO);
//
//    @Mapping(source = "id", target = "id")
//    TreatmentDTO toDto(Treatment treatment);

    default Treatment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Treatment treatment = new Treatment();
        treatment.setId(id);
        return treatment;
    }

}
