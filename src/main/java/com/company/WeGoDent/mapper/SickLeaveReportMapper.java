package com.company.WeGoDent.mapper;


import com.company.WeGoDent.dto.SickLeaveReportDTO;
import com.company.WeGoDent.entity.SickLeaveReport;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SickLeaveReportMapper extends EntityMapper<SickLeaveReportDTO, SickLeaveReport> {

    @Override
    @Mapping(source = "reportContentId", target = "reportContent.id")
    SickLeaveReport toEntity(SickLeaveReportDTO dto);

    @Override
    @Mapping(source = "reportContent.id", target = "reportContentId")
    SickLeaveReportDTO toDto(SickLeaveReport entity);
}
