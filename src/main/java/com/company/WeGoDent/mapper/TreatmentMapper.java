package com.company.WeGoDent.mapper;


import com.company.WeGoDent.dto.TreatmentDTO;
import com.company.WeGoDent.dto.TreatmentPhaseDTO;
import com.company.WeGoDent.entity.Treatment;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreatmentMapper extends EntityMapper<TreatmentDTO, Treatment> {




    //    default Treatment fromId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        Treatment treatment = new Treatment();
//        treatment.setId(id);
//        return treatment;
//    }



}
