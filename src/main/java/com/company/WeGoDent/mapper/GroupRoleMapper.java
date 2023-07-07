package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.GroupRoleDTO;
import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface GroupRoleMapper extends EntityMapper<GroupRoleDTO, GroupRole> {

    default List<Long> map(List<GroupRole> value) {
        if (value == null) {
            return null;
        }
        return value.stream()
                .map(GroupRole::getId)
                .collect(Collectors.toList());
    }

    default List<GroupRole> reverseMap(List<Long> value) {
        if (value == null) {
            return null;
        }
        return value.stream()
                .map(id -> {
                    GroupRole groupRole = new GroupRole();
                    groupRole.setId(id);
                    return groupRole;
                })
                .collect(Collectors.toList());
    }

}
