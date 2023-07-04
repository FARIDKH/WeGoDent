package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.BlogCategoryDTO;
import com.company.WeGoDent.entity.BlogCategory;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BlogCategoryMapper extends EntityMapper<BlogCategoryDTO, BlogCategory> {
}
