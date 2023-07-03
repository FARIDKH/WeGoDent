package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.GroupRoleDTO;
import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface GroupRoleMapper extends EntityMapper<GroupRoleDTO, GroupRole> {
}
